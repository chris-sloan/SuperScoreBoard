package com.chrissloan.superscoreboard.fixtures

import com.chrissloan.superscoreboard.model.Fixtures

interface FixtureListRepository {
    suspend fun getFixtures(): Fixtures
}