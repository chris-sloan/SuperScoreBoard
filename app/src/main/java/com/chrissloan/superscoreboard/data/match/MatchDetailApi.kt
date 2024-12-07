package com.chrissloan.superscoreboard.data.match

import com.chrissloan.superscoreboard.data.network.BaseApi
import com.chrissloan.superscoreboard.domain.common.model.Match
import io.ktor.client.call.body
import kotlinx.serialization.json.Json

class MatchDetailApi : BaseApi() {
    suspend fun getMatch(id: Int) =
        // we would use the id here to fetch the correct match
        try {
            Json.decodeFromString<Match>(fetch("match.json").body())
        } catch (e: Exception) {
            println("<<<<< - getMatch : $e")
            null
        }
}