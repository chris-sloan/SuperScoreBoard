package com.chrissloan.superscoreboard.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme()

private val LightColorScheme = lightColorScheme()

data class FixedAccentColors(
    val upComingBackground: Color,
    val onUpcomingBackground: Color,
    val inProgressBackground: Color,
    val onInProgressBackground: Color,
    val completedBackground: Color,
    val onCompletedBackground: Color,
)

fun getFixedAccentColors() = FixedAccentColors(
    upComingBackground = LightColorScheme.background,
    onUpcomingBackground = LightColorScheme.onBackground,
    inProgressBackground = inProgressBackground,
    onInProgressBackground = LightColorScheme.secondary,
    completedBackground = LightColorScheme.errorContainer,
    onCompletedBackground = LightColorScheme.secondary,
)

val LocalFixedAccentColors = compositionLocalOf { getFixedAccentColors() }

@Composable
fun SuperScoreBoardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
