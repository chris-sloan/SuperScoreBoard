package com.chrissloan.superscoreboard.data.fixtures

import com.chrissloan.superscoreboard.data.network.BaseApi
import com.chrissloan.superscoreboard.domain.common.model.Fixtures
import com.chrissloan.superscoreboard.domain.common.model.FixturesItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class FixturesApi: BaseApi() {

    suspend fun getFixtures() =
        try {
            val list = Json.decodeFromString<List<FixturesItem>>(fetch("fixtures.json").body())
            Fixtures(list)
        } catch (e: Exception) {
            null
        }
}