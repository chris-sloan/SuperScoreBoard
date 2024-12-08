package com.chrissloan.superscoreboard.match



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrissloan.superscoreboard.model.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MatchDetailViewModel(
    private val id: Int,
    private val matchDetailRepository: MatchDetailRepository
) : ViewModel() {
    private val uiStateEmitter = MutableStateFlow(value = MatchDetailUiState())
    val uiState: Flow<MatchDetailUiState> = uiStateEmitter

    init {
        viewModelScope.launch {
            uiStateEmitter.value = MatchDetailUiState(matchDetailRepository.getMatchDetail(id))
        }
    }

    data class MatchDetailUiState(
        val match: Match = Match()
    )
}


