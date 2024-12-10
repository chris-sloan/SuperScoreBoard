package com.chrissloan.superscoreboard.fixtures.viewmodel

class FixturesEventHandler {
    /**
     * Sometimes  we need to handle events that have other side effects.
     * Perhaps we need to launch another activity for user authentication for example.
     * We would cover these things in here.
     *
     * If the event is handled in here then we return true.
     */
    operator fun invoke(fixturesEvent: FixturesEvent): Boolean {
        return when (fixturesEvent) {
            is FixturesEvent.FixturesLoaded -> false
            FixturesEvent.Init -> false
        }
    }
}
