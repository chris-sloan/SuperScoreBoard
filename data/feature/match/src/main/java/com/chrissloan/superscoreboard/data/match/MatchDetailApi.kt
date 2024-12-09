package com.chrissloan.superscoreboard.data.match

import com.chrissloan.superscoreboard.data.network.BaseApi
import com.chrissloan.superscoreboard.model.Match


class MatchDetailApi : BaseApi() {
    @Suppress("IgnoreUnusedParameter")
    // we would use the id here to fetch the correct match
    suspend fun getMatch(id: Int) = fetch<Match>("match.json")
}
