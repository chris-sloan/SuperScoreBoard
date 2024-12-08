package com.chrissloan.superscoreboard.data.fixtures

import com.chrissloan.superscoreboard.fixtures.FixtureListRepository
import com.chrissloan.superscoreboard.model.Fixtures
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FixtureListRepositoryImplTest {

    private lateinit var fixtureListRepository: FixtureListRepository
    private lateinit var fixturesApi: FixturesApi

    @Before
    fun setUp() {
        fixturesApi = mockk()
        fixtureListRepository = FixtureListRepositoryImpl(fixturesApi)
    }

    @Test
    fun `getFixtures returns fixtures from api`() =
        runTest {
            val expectedFixtures = Fixtures()
            coEvery { fixturesApi.getFixtures() } returns expectedFixtures

            val actualFixtures = fixtureListRepository.getFixtures().first()

            assertEquals(expectedFixtures, actualFixtures)
        }

    @Test
    fun `getFixtures returns empty fixtures when api returns null`() =
        runTest {
            coEvery { fixturesApi.getFixtures() } returns null

            val actualFixtures = fixtureListRepository.getFixtures()

            assertNotNull(actualFixtures)
        }
}

