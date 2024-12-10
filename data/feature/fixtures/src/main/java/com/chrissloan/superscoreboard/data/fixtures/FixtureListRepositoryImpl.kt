package com.chrissloan.superscoreboard.data.fixtures

import com.chrissloan.superscoreboard.fixtures.FixtureListRepository
import com.chrissloan.superscoreboard.model.Fixtures
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class FixtureListRepositoryImpl(
    private val fixturesApi: FixturesApi,
) : FixtureListRepository {

    override suspend fun getFixtures(): Flow<Fixtures> =
        withContext(Dispatchers.IO) {
            flow {
                emit(fixturesApi.getFixtures() ?: Fixtures())
            }
        }
}
