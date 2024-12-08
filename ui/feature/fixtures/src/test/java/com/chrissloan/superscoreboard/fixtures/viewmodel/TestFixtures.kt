package com.chrissloan.superscoreboard.fixtures.viewmodel

import com.chrissloan.superscoreboard.model.Clock
import com.chrissloan.superscoreboard.model.FixturesItem
import com.chrissloan.superscoreboard.model.Kickoff
import com.chrissloan.superscoreboard.model.Team
import com.chrissloan.superscoreboard.model.TeamX

internal object TestFixtures {
    val inProgressFixturesItem = FixturesItem(
        id = 76,
        attendance = 32456,
        clock = Clock(label = "testClockLabel", secs = 324),
        teams = listOf(
            Team(score = 2, team = TeamX(id = 1, shortName = "HomeTeamName")),
            Team(score = 4, team = TeamX(id = 2, shortName = "AwayTeamName")),
        ),
        status = "I"
    )
    val completedFixturesItem = inProgressFixturesItem.copy(status = "C")
    val upcomingFixturesItem = inProgressFixturesItem.copy(
        status = "U",
        kickoff = Kickoff(millis = 123456789L)
    )

}