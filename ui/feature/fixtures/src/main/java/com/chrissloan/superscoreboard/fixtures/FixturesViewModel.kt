package com.chrissloan.superscoreboard.fixtures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrissloan.superscoreboard.model.Fixtures
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FixturesViewModel(
    private val fixtureListRepository: FixtureListRepository
): ViewModel() {

    private val uiStateEmitter = MutableStateFlow(value = FixturesUiState())
    val uiState: Flow<FixturesUiState> = uiStateEmitter

    init {
        viewModelScope.launch {
            uiStateEmitter.value = FixturesUiState(fixtureListRepository.getFixtures())
        }
    }

    data class FixturesUiState(
        val fixtures: Fixtures = Fixtures.Empty
    )
}