package com.chrissloan.superscoreboard.match

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.chrissloan.superscoreboard.common.LoadingSpinner
import com.chrissloan.superscoreboard.common.TeamBadge
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState.MatchState
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState.MatchState.ScoreState
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState.MatchState.ScorerState
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState.MatchState.TeamState
import com.chrissloan.superscoreboard.theme.LocalFixedAccentColors

@Composable
fun MatchDetailCard(match: MatchState) {
    Column(
        verticalArrangement = Arrangement.Top
    ) {
        if (match.score.homeScore < 0) {
            LoadingSpinner()
        } else {
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 24.dp)
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 24.dp)
                ) {
                    item { Score(match.homeTeam, match.awayTeam, match.score) }
                    scorers(this, match.scorers)
                    item { HorizontalDivider(modifier = Modifier.padding(8.dp)) }
                    item { MatchDetails(match = match.details) }
                }
            }
        }
    }
}

@Composable
fun Score(homeTeam: TeamState, awayTeam: TeamState, score: ScoreState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TeamBadge(id = homeTeam.id, modifier = Modifier.size(48.dp))
        TeamNameAbbr(
            abbreviatedName = homeTeam.name,
            textAlign = TextAlign.Start
        )
        ScoreBox(
            homeScore = score.homeScore,
            awayScore = score.awayScore,
            homeHtScore = score.homeHtScore,
            awayHtScore = score.awayHtScore
        )
        TeamNameAbbr(
            abbreviatedName = awayTeam.name,
            textAlign = TextAlign.End
        )
        TeamBadge(id = awayTeam.id, modifier = Modifier.size(48.dp))
    }
}

@Composable
fun RowScope.TeamNameAbbr(
    abbreviatedName: String,
    textAlign: TextAlign,
) {
    Text(
        modifier = Modifier
            .padding(8.dp)
            .weight(1f),
        text = abbreviatedName,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun ScoreBox(homeScore: Int, awayScore: Int, homeHtScore: Int, awayHtScore: Int) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(LocalFixedAccentColors.current.inProgressBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 2.dp),
            text = "$homeScore - $awayScore",
            maxLines = 1,
            color = LocalFixedAccentColors.current.onInProgressBackground,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier.padding(
                horizontal = 6.dp,
                vertical = 2.dp
            ),
            text = "HT $homeHtScore - $awayHtScore",
            color = LocalFixedAccentColors.current.onInProgressBackground,
            maxLines = 1,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )
    }
}


fun scorers(scope: LazyListScope, scorers: List<ScorerState>) {
    if (scorers.isEmpty()) return
    scorers.forEach { scorer ->
        scope.item {
            val scorerText =
                buildAnnotatedString {
                    append(scorer.time)
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(" Goal ")
                    }
                    append("- ${scorer.playerName}")
                }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    text = if (scorer.homePlayer) scorerText else AnnotatedString(
                        ""
                    ),
                    textAlign = TextAlign.End
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    text = if (!scorer.homePlayer) scorerText else AnnotatedString(
                        ""
                    ),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Composable
fun MatchDetails(match: MatchState.MatchDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text =
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Kick Off: ")
                    }
                    append(match.kickOffTime)
                },
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = match.date,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = match.location,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Attendance: ")
                }
                append("${match.attendance}")
            },
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Referee: ")
                }
                append(match.referee)
            },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}