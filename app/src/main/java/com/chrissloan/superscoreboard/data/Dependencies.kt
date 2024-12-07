package com.chrissloan.superscoreboard.data

import android.util.Log.v
import com.chrissloan.superscoreboard.data.fixtures.FixtureListRepositoryImpl
import com.chrissloan.superscoreboard.data.fixtures.FixturesApi
import com.chrissloan.superscoreboard.data.match.MatchDetailApi
import com.chrissloan.superscoreboard.data.match.MatchDetailRepositoryImpl
import com.chrissloan.superscoreboard.domain.fixture.FixtureListRepository
import com.chrissloan.superscoreboard.domain.match.MatchDetailRepository
import com.chrissloan.superscoreboard.ui.fixtures.FixturesViewModel
import com.chrissloan.superscoreboard.ui.match.MatchDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object Dependencies {
    val dataDependencies = module {
        singleOf(::FixtureListRepositoryImpl) { bind<FixtureListRepository>() }
        factoryOf(::FixturesApi)

        singleOf(::MatchDetailRepositoryImpl) { bind<MatchDetailRepository>() }
        factoryOf(::MatchDetailApi)
    }

    val uiDependencies = module {
        viewModelOf(::FixturesViewModel)
        viewModelOf(::MatchDetailViewModel)
    }
}