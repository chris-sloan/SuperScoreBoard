package com.chrissloan.superscoreboard.data.fixtures

import com.chrissloan.superscoreboard.data.network.BaseApi
import com.chrissloan.superscoreboard.model.Fixtures
import com.chrissloan.superscoreboard.model.FixturesItem
import io.ktor.client.call.body
import kotlinx.serialization.json.Json

class FixturesApi: BaseApi() {

    suspend fun getFixtures() : Fixtures? =
        try {
            val list = Json.decodeFromString<List<FixturesItem>>(fetch("fixtures.json").body())
            Fixtures(list)
        } catch (e: Exception) {
            println("<<<<< - getFixtures : $e")
            null
        }
}