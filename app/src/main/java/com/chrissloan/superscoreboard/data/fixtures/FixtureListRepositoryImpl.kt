package com.chrissloan.superscoreboard.data.fixtures

import com.chrissloan.superscoreboard.domain.common.model.Fixtures
import com.chrissloan.superscoreboard.domain.fixture.FixtureListRepository


class FixtureListRepositoryImpl(
    private val fixturesApi: FixturesApi,
) : FixtureListRepository {

    override suspend fun getFixtures() = fixturesApi.getFixtures() ?: Fixtures()
}