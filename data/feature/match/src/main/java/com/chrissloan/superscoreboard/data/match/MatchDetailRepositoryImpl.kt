package com.chrissloan.superscoreboard.data.match

import com.chrissloan.superscoreboard.match.MatchDetailRepository
import com.chrissloan.superscoreboard.model.Match
import jdk.jfr.internal.OldObjectSample.emit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class MatchDetailRepositoryImpl(
    private val matchDetailApi: MatchDetailApi,
) : MatchDetailRepository {
    override fun getMatchDetail(id: Int): Flow<Match> {
        return flow {
            emit(matchDetailApi.getMatch(id) ?: Match())
        }
    }
}