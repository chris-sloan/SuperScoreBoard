package com.chrissloan.superscoreboard.fixtures

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chrissloan.superscoreboard.common.TeamBadge
import com.chrissloan.superscoreboard.model.Fixtures
import com.chrissloan.superscoreboard.model.FixturesItem
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun FixtureList(
    fixtures: Fixtures,
    onItemClick: (FixturesItem) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        fixtures.fixtures.forEach { item ->
            item {
                Fixture(
                    item = item,
                    onItemClick = onItemClick,
                )
            }
        }
    }
}


@Composable
fun Fixture(
    item: FixturesItem,
    onItemClick: (FixturesItem) -> Unit,
) {
    Column(
        modifier = Modifier
    ) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    if (item.status != "U") {
                        onItemClick(item)
                    }
                },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            border = if (item.status == "I") BorderStroke(1.dp, Color(0xFF51E79A)) else null,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeTeam(item.teams[0].team.shortName)
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TeamBadge(
                        id = item.teams[0].team.id,
                        modifier = Modifier
                            .size(32.dp)
                            .weight(1f)
                    )
                    ScoreBox(item)
                    TeamBadge(id = item.teams[1].team.id,
                        modifier = Modifier
                            .size(32.dp)
                            .weight(1f))
                }
                AwayTeam(item.teams[1].team.shortName)
            }
        }
    }
}

@Composable
fun RowScope.HomeTeam(teamName: String) {
    Text(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .weight(1f),
        text = teamName,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun RowScope.AwayTeam(teamName: String) {
    Text(
        textAlign = TextAlign.End,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .weight(1f),
        text = teamName
    )
}

@Composable
private fun ScoreBox(item: FixturesItem) {
    when (item.status) {
        "U" -> UpcomingScoreBox(item)

        "C" -> CompletedScoreBox(item)

        "I" -> InProgressScoreBox(item)
    }
}

@Composable
fun UpcomingScoreBox(item: FixturesItem) {
    Text(
        modifier = Modifier.padding(horizontal = 8.dp),
        text = formatFutureMatch(item),
        maxLines = 1,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
fun CompletedScoreBox(item: FixturesItem) {
    Text(
        modifier = Modifier
            .clip(
                RoundedCornerShape(4.dp)
            )
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(horizontal = 6.dp, vertical = 2.dp),
        text = formatHistoricMatch(item),
        maxLines = 1,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun InProgressScoreBox(item: FixturesItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFF51E79A))
                .padding(horizontal = 6.dp, vertical = 2.dp),
            text = formatHistoricMatch(item),
            color = MaterialTheme.colorScheme.secondary,
            maxLines = 1,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier.padding(
                horizontal = 6.dp,
                vertical = 2.dp
            ),
            text = "${(item.clock.secs / 60).toInt()}'",
            maxLines = 1,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

private fun formatFutureMatch(item: FixturesItem): String {
    val match = Instant.fromEpochMilliseconds(item.kickoff.millis)
    val localDateTime = match.toLocalDateTime(TimeZone.currentSystemDefault())
    return "${localDateTime.hour.toString().padStart(2, '0')}:${
        localDateTime.minute.toString().padStart(2, '0')
    }"
}

private fun formatHistoricMatch(item: FixturesItem): String {
    item.teams[0].score
    return "${item.teams[0].score} : ${item.teams[1].score}"
}
