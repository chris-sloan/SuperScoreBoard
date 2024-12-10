@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.chrissloan.superscoreboard.ui


import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrissloan.superscoreboard.fixtures.FixturesScreen
import com.chrissloan.superscoreboard.match.MatchDetailScreen
import com.chrissloan.superscoreboard.theme.SuperScoreBoardTheme
import com.chrissloan.superscoreboard.useraction.NavigationAction


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        setContent {
            SuperScoreBoardTheme {
                SuperScoreBoard()
            }
        }
    }
}

@Composable
fun SuperScoreBoard() {

    val navigator = rememberListDetailPaneScaffoldNavigator<Int>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    val navigationHandler: (NavigationAction) -> Unit = { action ->
        when (action) {
            is NavigationAction.OnFixtureClick -> {
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, action.id)
            }
            // other navigation events
        }
    }

    Scaffold(containerColor = MaterialTheme.colorScheme.secondary) {
        ListDetailPaneScaffold(
            modifier = Modifier.padding(paddingValues = it),
            directive = navigator.scaffoldDirective,
            value = navigator.scaffoldValue,
            listPane = {
                AnimatedPane {
                    FixturesScreen(navigationHandler = navigationHandler)
                }
            },
            detailPane = {
                AnimatedPane {
                    navigator.currentDestination?.content?.let {
                        MatchDetailScreen(it)
                    }
                }
            },
        )
    }
}
