package com.chrissloan.superscoreboard.data.match

import com.chrissloan.superscoreboard.model.Match
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MatchDetailRepositoryImplTest {

    private lateinit var matchDetailRepository: MatchDetailRepositoryImpl
    private lateinit var matchDetailApi: MatchDetailApi

    @Before
    fun setUp() {
        matchDetailApi = mockk()
        matchDetailRepository = MatchDetailRepositoryImpl(matchDetailApi)
    }

    @Test
    fun `getMatchDetail returns match from api`() = runTest {
        val expectedMatch = Match()
        val matchId = 123
        coEvery { matchDetailApi.getMatch(matchId) } returns expectedMatch

        val actualMatch = matchDetailRepository.getMatchDetail(matchId)

        assertEquals(expectedMatch, actualMatch)
    }

    @Test
    fun `getMatchDetail returns empty match when api returns null`() = runTest {
        val matchId = 456
        coEvery { matchDetailApi.getMatch(matchId) } returns null

        val actualMatch = matchDetailRepository.getMatchDetail(matchId)

        assertNotNull(actualMatch)
    }
}