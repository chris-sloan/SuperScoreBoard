package com.chrissloan.superscoreboard.fixtures.viewmodel

import com.chrissloan.superscoreboard.model.Fixtures
import org.junit.Assert.assertFalse
import org.junit.Test

class FixturesEventHandlerTest {

    private val fixturesEventHandler = FixturesEventHandler()

    @Test
    fun `test Init event is not handled`() {
        val event = FixturesEvent.Init

        val handled = fixturesEventHandler(event)

        assertFalse(handled)
    }

    @Test
    fun `test FixturesLoaded event is not handled`() {
        val event = FixturesEvent.FixturesLoaded(Fixtures())

        val handled = fixturesEventHandler(event)

        assertFalse(handled)
    }

}