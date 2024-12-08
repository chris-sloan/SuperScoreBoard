package com.chrissloan.superscoreboard.data.fixtures

import com.chrissloan.superscoreboard.fixtures.FixtureListRepository
import com.chrissloan.superscoreboard.model.Fixtures
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FixtureListRepositoryImpl(
    private val fixturesApi: FixturesApi,
) : FixtureListRepository {

    override fun getFixtures(): Flow<Fixtures> =
        flow {
            emit (fixturesApi.getFixtures() ?: Fixtures())
        }
}