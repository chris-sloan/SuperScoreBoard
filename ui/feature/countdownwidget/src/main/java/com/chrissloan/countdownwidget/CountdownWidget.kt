package com.chrissloan.countdownwidget

    import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.glance.*
import androidx.glance.appwidget.*
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.DurationUnit


private val TARGET_MS = longPreferencesKey("targetUtcMs")

class CountdownWidget : GlanceAppWidget() {

    override val stateDefinition = PreferencesGlanceStateDefinition

    override val sizeMode = SizeMode.Responsive(
        setOf(
            DpSize(120.dp, 60.dp),
            DpSize(180.dp, 60.dp),
            DpSize(240.dp, 60.dp)
        )
    )

    override fun onCompositionError(
        context: Context,
        glanceId: GlanceId,
        appWidgetId: Int,
        throwable: Throwable
    ) {
        super.onCompositionError(context, glanceId, appWidgetId, throwable)
        println("<<<<< - Composition Error : $throwable")
    }

    override suspend fun onDelete(context: Context, glanceId: GlanceId) {
        super.onDelete(context, glanceId)
        println("<<<<< - Widget was deleted")
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val targetUtc = currentState(TARGET_MS)
            if (targetUtc == null) {
                val scope = rememberCoroutineScope()
                LaunchedEffect(Unit) {
                    scope.launch {
                        updateAppWidgetState(context, id) {
                            it[TARGET_MS] = 1751119200000 // 15:00:00 28/06/2025
                        }
                    }
                }
                Box(
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .appWidgetBackground()
                        .background(Color.Red)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tap to set\na target date",
                        style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center)
                    )
                }
                return@provideContent
            }

            MyContent(targetUtc = targetUtc)
        }
    }
}

@Composable
private fun MyContent(targetUtc: Long) {
    val now: Instant = Clock.System.now()
    val target: Instant = Instant.fromEpochMilliseconds(targetUtc)
    val remaining: Duration = (target - now).coerceAtLeast(ZERO)

    /* Split into days / hours / minutes */
    val days  = remaining.toInt(DurationUnit.DAYS)
    val hours = (remaining - days.days).toInt(DurationUnit.HOURS)
    val minutes  = (remaining - days.days - hours.hours).toInt(DurationUnit.MINUTES)

    val isSmall = LocalSize.current.height <= 120.dp

    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .appWidgetBackground()
            .background(GlanceTheme.colors.primary)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "%dd  %02dh  %02dm".format(days, hours, minutes),
                style = TextStyle(
                    color = GlanceTheme.colors.onPrimary,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.None,
                    fontFamily = FontFamily.Monospace,
                )
            )
            Text(
                "until launch 🚀",
                style = TextStyle(
                    color = GlanceTheme.colors.inversePrimary,
                    fontSize = if (isSmall) 18.sp else 24.sp)
            )
        }
    }
}

class CountdownReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = CountdownWidget()
}
