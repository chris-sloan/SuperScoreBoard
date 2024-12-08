package com.chrissloan.superscoreboard.match

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MatchDetailScreen(
    itemId: Int,
    viewModel: MatchDetailViewModel = koinViewModel { parametersOf(itemId) },
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle(initialValue = MatchDetailViewModel.MatchDetailUiState())
    MatchDetailCard(uiState.match)
}

