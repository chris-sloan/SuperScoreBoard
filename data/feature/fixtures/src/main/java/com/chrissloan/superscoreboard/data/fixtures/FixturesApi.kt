package com.chrissloan.superscoreboard.data.fixtures

import com.chrissloan.superscoreboard.data.network.BaseApi
import com.chrissloan.superscoreboard.model.Fixtures
import com.chrissloan.superscoreboard.model.FixturesItem

class FixturesApi : BaseApi() {

    suspend fun getFixtures(): Fixtures? =
        fetch<List<FixturesItem>>("fixtures.json")?.let {
            Fixtures(it)
        }
}