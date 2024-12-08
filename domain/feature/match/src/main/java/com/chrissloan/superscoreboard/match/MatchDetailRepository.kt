package com.chrissloan.superscoreboard.match

import com.chrissloan.superscoreboard.model.Match

interface MatchDetailRepository {
    suspend fun getMatchDetail(id: Int): Match
}