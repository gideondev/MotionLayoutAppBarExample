package com.gideopaul.myapplication

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout

private enum class MotionLayoutAppBarItem {
    BACK_BUTTON,
    TITLE,
    SUBTITLE,
    BACKGROUND_BOX
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionLayoutAppBar(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    elevation: Dp = 4.dp,
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
        Surface(
            modifier = Modifier.layoutId(MotionLayoutAppBarItem.BACKGROUND_BOX),
            elevation = elevation,
            color = backgroundColor,
            content = {}
        )

        IconButton(
            modifier = Modifier.layoutId(MotionLayoutAppBarItem.BACK_BUTTON),
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
            modifier = Modifier.layoutId(MotionLayoutAppBarItem.TITLE),
            text = title,
            style = MaterialTheme.typography.h6,
            color = contentColor
        )

        Text(
            modifier = Modifier.layoutId(MotionLayoutAppBarItem.SUBTITLE),
            text = subTitle,
            style = MaterialTheme.typography.subtitle1,
            color = contentColor
        )
    }
}

private fun startConstraintSet() = ConstraintSet {
    val backButton = createRefFor(MotionLayoutAppBarItem.BACK_BUTTON)
    val title = createRefFor(MotionLayoutAppBarItem.TITLE)
    val subtitle = createRefFor(MotionLayoutAppBarItem.SUBTITLE)
    val backgroundBox = createRefFor(MotionLayoutAppBarItem.BACKGROUND_BOX)

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
    val backButton = createRefFor(MotionLayoutAppBarItem.BACK_BUTTON)
    val title = createRefFor(MotionLayoutAppBarItem.TITLE)
    val subtitle = createRefFor(MotionLayoutAppBarItem.SUBTITLE)
    val backgroundBox = createRefFor(MotionLayoutAppBarItem.BACKGROUND_BOX)

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
                    delayMillis = 1000,
                    durationMillis = 1000,
                    easing = LinearEasing
                )
            )
        )
    }

    MotionLayoutAppBar(
        title = "Title",
        subTitle = "Subtitle",
        backgroundColor = Color(0xFF214561),
        progress = motionLayoutProgress.value
    )
}

@Preview
@Composable
fun InitialStatePreview() {
    MotionLayoutAppBar(
        title = "Title",
        subTitle = "Subtitle",
        backgroundColor = Color(0xFF214561),
        progress = 0.0f
    )
}

@Preview
@Composable
fun FinalStatePreview() {
    MotionLayoutAppBar(
        title = "Title",
        subTitle = "Subtitle",
        backgroundColor = Color(0xFF214561),
        progress = 1.0f
    )
}
