package com.chrissloan.superscoreboard.useraction

sealed interface UserAction

sealed interface NavigationAction : UserAction {
    data class OnFixtureClick(val id: Int) : NavigationAction
    // other navigation actions could go here.
}

sealed interface FixturesAction: UserAction {
    data class OnFixtureClick(val id: Int) : FixturesAction
    // other fixtures specific actions here
}

sealed interface MatchDetailAction: UserAction
    // any match detail actions here
    // club badge / name click to show club details
    // scprer click to show player stats.
    // etc