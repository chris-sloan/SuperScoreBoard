package com.chrissloan.superscoreboard.application

import com.chrissloan.superscoreboard.application.Constants.POLLING_INTERVAL
import com.chrissloan.superscoreboard.data.fixtures.FixtureListRepositoryImpl
import com.chrissloan.superscoreboard.data.fixtures.FixturesApi
import com.chrissloan.superscoreboard.data.match.MatchDetailApi
import com.chrissloan.superscoreboard.data.match.MatchDetailRepositoryImpl
import com.chrissloan.superscoreboard.fixtures.FixtureListRepository
import com.chrissloan.superscoreboard.fixtures.viewmodel.FixturesEventHandler
import com.chrissloan.superscoreboard.fixtures.viewmodel.FixturesReducer
import com.chrissloan.superscoreboard.fixtures.viewmodel.FixturesViewModel
import com.chrissloan.superscoreboard.match.MatchDetailRepository
import com.chrissloan.superscoreboard.match.viewmodel.MatchDetailReducer
import com.chrissloan.superscoreboard.match.viewmodel.MatchDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object Dependencies {
    val dataDependencies = module {
        factoryOf(::FixturesApi)
        factoryOf(::MatchDetailApi)
    }

    val domainDependencies = module {
        singleOf(::FixtureListRepositoryImpl) { bind<FixtureListRepository>() }
        single<MatchDetailRepository> {
            MatchDetailRepositoryImpl(
                matchDetailApi = get(),
                matchDetailPollingInterval = POLLING_INTERVAL
            )
        }
    }

    val uiDependencies = module {
        factoryOf(::FixturesReducer)
        factoryOf(::FixturesEventHandler)
        viewModelOf(::FixturesViewModel)

        factoryOf(::MatchDetailReducer)
        viewModelOf(::MatchDetailViewModel)
    }
}

object Constants {
    internal const val POLLING_INTERVAL = 30_000L
}
