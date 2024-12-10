package com.chrissloan.superscoreboard.match

import com.chrissloan.superscoreboard.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchDetailRepository {
    suspend fun getMatchDetail(id: Int): Flow<Match>
}
