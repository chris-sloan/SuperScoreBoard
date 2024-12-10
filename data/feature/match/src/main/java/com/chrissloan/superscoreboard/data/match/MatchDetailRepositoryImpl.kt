package com.chrissloan.superscoreboard.data.match

import com.chrissloan.superscoreboard.match.MatchDetailRepository
import com.chrissloan.superscoreboard.model.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MatchDetailRepositoryImpl(
    private val matchDetailApi: MatchDetailApi,
    private val matchDetailPollingInterval: Long
) : MatchDetailRepository {
    override suspend fun getMatchDetail(id: Int): Flow<Match> {
        return withContext(Dispatchers.IO) {
            flow {
                while (true) {
                    matchDetailApi.getMatch(id)?.let {
                        emit(it)
                    }
                    delay(matchDetailPollingInterval)
                }
            }
        }
    }
}
