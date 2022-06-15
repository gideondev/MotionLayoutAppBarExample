package com.gideopaul.myapplication

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionLayoutAppBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onPrimary,
    progress: Float = 0.0f
) {
    MotionLayout(
        modifier = modifier.fillMaxWidth(),
        start = startConstraintSet(),
        end = endConstraintSet(),
        progress = progress
    ) {
        Box(
            modifier = Modifier
                .layoutId("background")
                .background(backgroundColor)
        )

        IconButton(
            modifier = Modifier.layoutId("back_button"),
            onClick = {
                onBackPressed()
            }
        ) {
            Icon(
                Icons.Default.ArrowBack,
                "Back Button",
                tint = contentColor
            )
        }

        Text(
            modifier = Modifier.layoutId("title"),
            text = "Title",
            style = MaterialTheme.typography.h5,
            color = contentColor
        )

        Text(
            modifier = Modifier.layoutId("subtitle"),
            text = "Subtitle",
            style = MaterialTheme.typography.h6,
            color = contentColor
        )
    }
}

private fun startConstraintSet() = ConstraintSet {
    val backButton = createRefFor("back_button")
    val title = createRefFor("title")
    val subtitle = createRefFor("subtitle")
    val backgroundBox = createRefFor("background")

    constrain(backButton) {
        top.linkTo(parent.top, 16.dp)
        start.linkTo(parent.start, 16.dp)
        bottom.linkTo(parent.bottom, 16.dp)
    }

    constrain(title) {
        top.linkTo(parent.top, 16.dp)
        start.linkTo(backButton.end, 16.dp)
    }

    constrain(subtitle) {
        top.linkTo(title.bottom, 4.dp)
        start.linkTo(title.start)
        bottom.linkTo(parent.bottom, 16.dp)
    }

    constrain(backgroundBox) {
        width = Dimension.matchParent
        height = Dimension.fillToConstraints

        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }
}

private fun endConstraintSet() = ConstraintSet {
    val backButton = createRefFor("back_button")
    val title = createRefFor("title")
    val subtitle = createRefFor("subtitle")
    val backgroundBox = createRefFor("background")

    constrain(backButton) {
        top.linkTo(parent.top, 16.dp)
        start.linkTo(parent.start, 16.dp)
    }

    constrain(title) {
        top.linkTo(backButton.bottom, 16.dp)
        start.linkTo(backButton.start, 16.dp)
    }

    constrain(subtitle) {
        top.linkTo(title.bottom, 8.dp)
        start.linkTo(title.start)
        bottom.linkTo(parent.bottom, 16.dp)
    }

    constrain(backgroundBox) {
        width = Dimension.matchParent
        height = Dimension.fillToConstraints

        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }
}

@Preview
@Composable
fun PreviewMotionLayoutAppBar() {
    val motionLayoutProgress = remember { Animatable(0.0f) }

    LaunchedEffect(Unit) {
        motionLayoutProgress.animateTo(
            1.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1500,
                    easing = LinearEasing
                )
            )
        )
    }

    MotionLayoutAppBar(
        progress = motionLayoutProgress.value
    )
}
