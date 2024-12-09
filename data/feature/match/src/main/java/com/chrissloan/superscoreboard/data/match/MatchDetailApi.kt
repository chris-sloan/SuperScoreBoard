package com.chrissloan.superscoreboard.data.match

import com.chrissloan.superscoreboard.data.network.BaseApi
import kotlinx.serialization.json.Json
import com.chrissloan.superscoreboard.model.Match
import io.ktor.client.call.body


class MatchDetailApi : BaseApi() {
    @Suppress("IgnoreUnusedParameter")
    suspend fun getMatch(id: Int) =
        // we would use the id here to fetch the correct match
        try {
            Json.decodeFromString<Match>(fetch("match.json").body())
        } catch (e: Exception) {
            println("<<<<< - getMatch : $e")
            null
        }
}