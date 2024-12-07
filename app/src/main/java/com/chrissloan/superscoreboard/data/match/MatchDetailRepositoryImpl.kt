package com.chrissloan.superscoreboard.data.match

import com.chrissloan.superscoreboard.domain.common.model.Match
import com.chrissloan.superscoreboard.domain.match.MatchDetailRepository

class MatchDetailRepositoryImpl(
    private val matchDetailApi: MatchDetailApi,
) : MatchDetailRepository {
    override suspend fun getMatchDetail(id: Int): Match {
        return matchDetailApi.getMatch(id) ?: Match()
    }
}