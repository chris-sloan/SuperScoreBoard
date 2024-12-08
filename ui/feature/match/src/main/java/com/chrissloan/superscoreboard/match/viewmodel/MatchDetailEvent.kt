package com.chrissloan.superscoreboard.match.viewmodel

import com.chrissloan.superscoreboard.model.Match

sealed interface MatchDetailEvent {
    object Init : MatchDetailEvent
    data class MatchDetailLoaded(val match: Match) : MatchDetailEvent
}
