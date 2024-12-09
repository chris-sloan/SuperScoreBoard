@file:OptIn(ExperimentalMaterial3Api::class)

package com.chrissloan.superscoreboard.fixtures

import android.R.attr.text
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chrissloan.superscoreboard.common.LoadingSpinner
import com.chrissloan.superscoreboard.common.TeamBadge
import com.chrissloan.superscoreboard.fixtures.state.FixturesUiState.FixtureState
import com.chrissloan.superscoreboard.fixtures.state.FixturesUiState.FixtureState.MatchStatus
import com.chrissloan.superscoreboard.theme.LocalFixedAccentColors

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun FixtureList(
    fixtures: List<FixtureState>,
    onItemClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        if (fixtures.isEmpty()) {
            item {
                LoadingSpinner()
            }
        } else {
            fixtures.forEach { item ->
                item {
                    Fixture(
                        item = item,
                        onItemClick = onItemClick,
                    )
                }
            }
        }
    }
}


@Composable
fun Fixture(
    item: FixtureState,
    onItemClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    onItemClick(item.id)

                },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeTeam(item.homeTeam.name)
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TeamBadge(
                        id = item.homeTeam.id,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .weight(1f)
                    )
                    ScoreBox(item)
                    TeamBadge(
                        id = item.awayTeam.id,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .weight(1f)
                    )
                }
                AwayTeam(item.awayTeam.name)
            }
        }
    }
}

@Composable
fun RowScope.HomeTeam(teamName: String) {
    val style = if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        MaterialTheme.typography.titleSmall
    } else {
        MaterialTheme.typography.titleMedium
    }
    Text(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .weight(1f),
        text = teamName,
        textAlign = TextAlign.Start,
        style = style
    )
}

@Composable
fun RowScope.AwayTeam(teamName: String) {
    val style = if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        MaterialTheme.typography.titleSmall
    } else {
        MaterialTheme.typography.titleMedium
    }

    Text(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .weight(1f),
        text = teamName,
        textAlign = TextAlign.End,
        style = style
    )
}

@Composable
private fun ScoreBox(item: FixtureState) {
    when (item.status) {
        is MatchStatus.Upcoming -> UpcomingScoreBox(item.status)
        is MatchStatus.Completed -> CompletedScoreBox(item.status)
        is MatchStatus.InProgress -> InProgressScoreBox(item.status)
    }
}

@Composable
fun UpcomingScoreBox(item: MatchStatus.Upcoming) {
    Text(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .background(LocalFixedAccentColors.current.upComingBackground),
        text = item.time,
        maxLines = 1,
        color = LocalFixedAccentColors.current.onUpcomingBackground,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
fun CompletedScoreBox(item: MatchStatus.Completed) {
    Text(
        modifier = Modifier
            .clip(
                RoundedCornerShape(4.dp)
            )
            .background(LocalFixedAccentColors.current.completedBackground)
            .padding(horizontal = 6.dp, vertical = 2.dp),
        text = item.score,
        color = LocalFixedAccentColors.current.onCompletedBackground,
        maxLines = 1,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun InProgressScoreBox(item: MatchStatus.InProgress) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(LocalFixedAccentColors.current.inProgressBackground)
                .padding(horizontal = 6.dp, vertical = 2.dp),
            text = item.score,
            color = LocalFixedAccentColors.current.onInProgressBackground,
            maxLines = 1,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier.padding(
                horizontal = 6.dp,
                vertical = 2.dp
            ),
            color = LocalFixedAccentColors.current.onInProgressBackground,
            text = item.time,
            maxLines = 1,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )
    }
}