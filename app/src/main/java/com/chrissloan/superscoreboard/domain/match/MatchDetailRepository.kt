package com.chrissloan.superscoreboard.domain.match

import com.chrissloan.superscoreboard.domain.common.model.Match

interface MatchDetailRepository {
    suspend fun getMatchDetail(id: Int): Match
}
