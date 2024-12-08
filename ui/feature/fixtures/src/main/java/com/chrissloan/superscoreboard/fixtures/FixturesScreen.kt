package com.chrissloan.superscoreboard.fixtures

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chrissloan.superscoreboard.model.FixturesItem
import com.chrissloan.superscoreboard.theme.SuperScoreBoardTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun FixturesScreen(
    onItemClick: (FixturesItem) -> Unit,
    viewModel: FixturesViewModel = koinViewModel<FixturesViewModel>(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(initialValue = FixturesViewModel.FixturesUiState())
    FixtureList(uiState.fixtures, onItemClick)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperScoreBoardTheme {
        FixturesScreen({})
    }
}