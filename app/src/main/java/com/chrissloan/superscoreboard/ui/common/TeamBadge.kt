package com.chrissloan.superscoreboard.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun TeamBadge(
    id: Int,
    modifier:Modifier = Modifier
) {
    AsyncImage(
        modifier = modifier,
        model = "https://pyates-twocircles.github.io/two-circles-tech-test/images/${id}.png",
        contentDescription = null,
    )
}

