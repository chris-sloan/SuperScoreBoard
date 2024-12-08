package com.chrissloan.superscoreboard.application

import android.system.Os.bind
import com.chrissloan.superscoreboard.data.fixtures.FixtureListRepositoryImpl
import com.chrissloan.superscoreboard.data.fixtures.FixturesApi
import com.chrissloan.superscoreboard.data.match.MatchDetailApi
import com.chrissloan.superscoreboard.data.match.MatchDetailRepositoryImpl
import com.chrissloan.superscoreboard.fixtures.FixtureListRepository
import com.chrissloan.superscoreboard.fixtures.FixturesViewModel
import com.chrissloan.superscoreboard.match.MatchDetailRepository
import com.chrissloan.superscoreboard.match.MatchDetailViewModel
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
        singleOf(::MatchDetailRepositoryImpl) { bind<MatchDetailRepository>() }
    }

    val uiDependencies = module {
        viewModelOf(::FixturesViewModel)
        viewModelOf(::MatchDetailViewModel)
    }
}