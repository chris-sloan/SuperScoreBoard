package com.chrissloan.superscoreboard.match

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState
import com.chrissloan.superscoreboard.match.viewmodel.MatchDetailViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MatchDetailScreen(
    itemId: Int,
    viewModel: MatchDetailViewModel = koinViewModel { parametersOf(itemId) },
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(initialValue = MatchDetailUiState())

/*
    val actionHandler = { action: UserAction ->
        when (action) {
            is NavigationAction -> navigationHandler(action)
            is MatchDetailAction -> viewModel::onAction
            else -> Unit // ignore other actions.
        }
    }
*/
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

    // pass action handler down if needed.
    MatchDetailCard(uiState.match)
}
