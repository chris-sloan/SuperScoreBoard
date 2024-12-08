package com.chrissloan.superscoreboard.data.match

import com.chrissloan.superscoreboard.match.MatchDetailRepository
import com.chrissloan.superscoreboard.model.Match

class MatchDetailRepositoryImpl(
    private val matchDetailApi: MatchDetailApi,
) : MatchDetailRepository {
    override suspend fun getMatchDetail(id: Int): Match {
        return matchDetailApi.getMatch(id) ?: Match()
    }
}