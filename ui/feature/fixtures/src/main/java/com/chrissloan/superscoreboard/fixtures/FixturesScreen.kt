package com.chrissloan.superscoreboard.fixtures

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chrissloan.superscoreboard.fixtures.state.FixturesUiState
import com.chrissloan.superscoreboard.fixtures.viewmodel.FixturesViewModel
import com.chrissloan.superscoreboard.theme.SuperScoreBoardTheme
import com.chrissloan.superscoreboard.useraction.FixturesAction
import com.chrissloan.superscoreboard.useraction.NavigationAction
import com.chrissloan.superscoreboard.useraction.UserAction
import org.koin.androidx.compose.koinViewModel

@Composable
fun FixturesScreen(
    navigationHandler: (NavigationAction) -> Unit,
    viewModel: FixturesViewModel = koinViewModel<FixturesViewModel>(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(initialValue = FixturesUiState())

    val actionHandler: (UserAction) -> Unit = { action ->
        when (action) {
            is NavigationAction -> navigationHandler(action)
            is FixturesAction -> viewModel::onAction
            // I feel this needs an else.
        }
    }

    val currentLifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = currentLifecycleOwner) {
        val lifecycle = currentLifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> { viewModel.onLifeCycleResumed() }
                Lifecycle.Event.ON_PAUSE -> { viewModel.onLifecyclePaused() }
                else -> Unit
            }
        }
        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
    FixtureList(uiState.fixtures, actionHandler)
}

@Preview(showBackground = true)
@Composable
fun FixturesPreview() {
    SuperScoreBoardTheme {
        FixturesScreen({})
    }
}
