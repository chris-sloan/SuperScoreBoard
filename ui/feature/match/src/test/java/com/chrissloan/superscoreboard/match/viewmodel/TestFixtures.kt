package com.chrissloan.superscoreboard.match.viewmodel

import com.chrissloan.superscoreboard.model.Clock
import com.chrissloan.superscoreboard.model.Club
import com.chrissloan.superscoreboard.model.Event
import com.chrissloan.superscoreboard.model.Ground
import com.chrissloan.superscoreboard.model.HalfTimeScore
import com.chrissloan.superscoreboard.model.Kickoff
import com.chrissloan.superscoreboard.model.Lineup
import com.chrissloan.superscoreboard.model.Match
import com.chrissloan.superscoreboard.model.MatchOfficial
import com.chrissloan.superscoreboard.model.Name
import com.chrissloan.superscoreboard.model.Team
import com.chrissloan.superscoreboard.model.TeamLists
import com.chrissloan.superscoreboard.model.TeamX

internal object TestFixtures {
    val sampleMatch = Match(
        teams = listOf(
            Team(
                score = 2,
                team = TeamX(id = 1, club = Club(name = "Liverpool", abbr = "LIV"))
            ),
            Team(
                score = 1,
                team = TeamX(id = 2, club = Club(name = "Manchester United", abbr = "MUN"))
            )
        ),
        halfTimeScore = HalfTimeScore(awayScore = 0, homeScore = 1),
        events = listOf(
            Event(type = "G", clock = Clock("10'"), teamId = 1, personId = 123),
            Event(type = "G", clock = Clock("45'"), teamId = 1, personId = 456),
            Event(type = "G", clock = Clock("70'"), teamId = 2, personId = 789)
        ),
        teamLists = listOf(
            TeamLists(
                teamId = 1,
                lineup = listOf(
                    Lineup(id = 123, name = Name(last = "Salah")),
                    Lineup(id = 456, name = Name(last = "Mane"))
                )
            ),
            TeamLists(
                teamId = 2,
                lineup = listOf(Lineup(id = 789, name = Name(last = "Ronaldo")))
            )
        ),
        kickoff = Kickoff(label = "Saturday 10 September 2023, 15:00"),
        ground = Ground(name = "Anfield", city = "Liverpool"),
        attendance = 53394,
        matchOfficials = listOf(MatchOfficial(name = Name(display = "Michael Oliver")))
    )
}
