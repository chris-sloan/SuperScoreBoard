package com.chrissloan.superscoreboard.data.fixtures

import com.chrissloan.superscoreboard.fixtures.FixtureListRepository
import com.chrissloan.superscoreboard.model.Fixtures

class FixtureListRepositoryImpl(
    private val fixturesApi: FixturesApi,
) : FixtureListRepository {

    override suspend fun getFixtures(): Fixtures = fixturesApi.getFixtures() ?: Fixtures()
}