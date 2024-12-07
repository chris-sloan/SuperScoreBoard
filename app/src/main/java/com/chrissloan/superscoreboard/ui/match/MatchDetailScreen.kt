package com.chrissloan.superscoreboard.ui.match

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
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chrissloan.superscoreboard.domain.common.model.Match
import com.chrissloan.superscoreboard.ui.common.TeamBadge
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MatchDetailScreen(
    itemId: Int,
    viewModel: MatchDetailViewModel = koinViewModel { parametersOf(itemId) },
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle(initialValue = MatchDetailViewModel.MatchDetailUiState())

    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),

        ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item { ScoreRow(match = uiState.match) }
            scorersRow(this, uiState.match)
            item { HorizontalDivider(modifier = Modifier.padding(8.dp)) }
            item { MatchDetailRow(match = uiState.match) }
        }
    }
}


@Composable
fun ScoreRow(match: Match) {
    val teams = match.teams
    if (teams.isEmpty()) return
    val halfTimeScore = match.halfTimeScore
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TeamBadge(id = teams.first().team.id, modifier = Modifier.size(48.dp))
        TeamNameAbbr(
            abbreviatedName = teams.first().team.club.abbr,
            textAlign = TextAlign.Start
        )
        ScoreBox(
            homeScore = teams.first().score,
            awayScore = teams.last().score,
            homeHtScore = halfTimeScore.homeScore,
            awayHtScore = halfTimeScore.awayScore
        )
        TeamNameAbbr(
            abbreviatedName = teams.last().team.club.abbr,
            textAlign = TextAlign.End
        )
        TeamBadge(id = teams.last().team.id, modifier = Modifier.size(48.dp))
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
            .background(Color(0xFF51E79A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 2.dp),
            text = "$homeScore - $awayScore",
            maxLines = 1,
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
            maxLines = 1,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )
    }
}


fun scorersRow(scope: LazyListScope, match: Match) {
    val events = match.events.filter { it.type == "G" }
    if (events.isEmpty()) return
    val homeTeamId = match.teams.first().team.id
    val awayTeamId = match.teams.last().team.id
    events.forEach { event ->
        scope.item {
            match.teamLists
                .firstOrNull {
                    it.teamId == event.teamId
                }
                ?.lineup?.firstOrNull {
                    it.id == event.personId
                }
                ?.let { player ->
                    val playerName = player.name.last
                    val eventTime = event.clock.label.substring(0, 3)
                    val scorerText =
                        buildAnnotatedString {
                            append(eventTime)
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(" Goal ")
                            }
                            append("- $playerName")
                        }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp),
                            text = if (event.teamId == homeTeamId) scorerText else AnnotatedString(
                                ""
                            ),
                            textAlign = TextAlign.End
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp),
                            text = if (event.teamId == awayTeamId) scorerText else AnnotatedString(
                                ""
                            ),
                            textAlign = TextAlign.Start
                        )
                    }
                }
        }
    }
}

@Composable
fun MatchDetailRow(match: Match) {
    if (match.matchOfficials.isEmpty()) return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Kick Off: ${
                match.kickoff.label.split(",").lastOrNull()?.split(" ")?.dropWhile { it.isEmpty() }
                    ?.first().orEmpty()
            }"
        )
        Text(text = match.kickoff.label.split(",").first())
        Text(text = "${match.ground.name}, ${match.ground.city}")
        Text(text = "Attendance: ${match.attendance}")
        Text(text = "Referee: ${match.matchOfficials.first().name.display}")

    }
}