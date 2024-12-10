package com.chrissloan.superscoreboard.data.match

import app.cash.turbine.test
import com.chrissloan.superscoreboard.model.Match
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MatchDetailRepositoryImplTest {

    private lateinit var matchDetailRepository: MatchDetailRepositoryImpl
    private lateinit var matchDetailApi: MatchDetailApi

    @Before
    fun setUp() {
        matchDetailApi = mockk()
        matchDetailRepository = MatchDetailRepositoryImpl(matchDetailApi, 1000L)
    }

    @Test
    fun `getMatchDetail returns match from api`() =
        runTest {
            val expectedMatch = Match()
            val matchId = 123
            coEvery { matchDetailApi.getMatch(matchId) } returns expectedMatch

            val actualMatch = matchDetailRepository.getMatchDetail(matchId).first()

            assertEquals(expectedMatch, actualMatch)
        }

    @Test
    fun `getMatchDetail returns matches from api`() =
        runTest {
            val expectedMatch = Match()
            val matchId = 123
            coEvery { matchDetailApi.getMatch(matchId) } returns expectedMatch

            val flow = matchDetailRepository.getMatchDetail(matchId)
            val emissions = flow.take(2).toList()
            advanceTimeBy(1200L)

            assertEquals(2, emissions.size)
        }

    @Test()
    fun `getMatchDetail returns empty match when api returns null`() =
        runTest {
            val matchId = 456
            coEvery { matchDetailApi.getMatch(matchId) } returns null

            matchDetailRepository.getMatchDetail(matchId).test {
                expectNoEvents()
            }
        }
}
