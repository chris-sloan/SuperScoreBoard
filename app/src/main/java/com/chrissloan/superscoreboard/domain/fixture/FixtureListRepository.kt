package com.chrissloan.superscoreboard.domain.fixture

import com.chrissloan.superscoreboard.domain.common.model.Fixtures

interface FixtureListRepository {
    suspend fun getFixtures(): Fixtures
}

