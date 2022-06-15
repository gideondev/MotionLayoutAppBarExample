package com.gideopaul.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionLayoutAppBarDemo()
        }
    }
}

@Composable
private fun DummySection(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    height: Dp = 500.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(backgroundColor)
    )
}

@Composable
fun MotionLayoutAppBarDemo() {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            val sectionColors = listOf(
                Color(0XFFBBF6F3),
                Color(0XFFF6F3B5),
                Color(0XFFFFDCBE),
                Color(0XFFFDB9C9)
            )

            sectionColors.forEach { color ->
                DummySection(backgroundColor = color)
            }
        }

        MotionLayoutAppBar(
            title = "MotionLayoutActivity",
            subTitle = "Example",
            progress = getToolbarMotionLayoutProgress(
                currentScrollPosition = scrollState.value,
                startOfTransitionZone = 150,
                endOfTransitionZone = 250
            ),
        )
    }
}

private fun getToolbarMotionLayoutProgress(
    currentScrollPosition: Int,
    startOfTransitionZone: Int,
    endOfTransitionZone: Int
): Float {
    return when {
        // When the user scroll position is in the transition zone, for evert pixel scrolled, we
        // will compute the alpha value.
        currentScrollPosition in startOfTransitionZone..endOfTransitionZone -> {
            val pixelsScrolledAboveStartOfTransitionZone =
                currentScrollPosition - startOfTransitionZone
            pixelsScrolledAboveStartOfTransitionZone.toFloat() / 100
        }

        // When user scroll position is below the transition zone, alpha will always be 0f.
        // An example would be the initial state of the screen when user did not scroll at all.
        currentScrollPosition < startOfTransitionZone -> 0f

        // When user scroll position is above the transition zone, alpha will always be 1f.
        else -> 1f
    }
}

@Preview
@Composable
fun PreviewMotionLayoutAppBarDemo() {
    MotionLayoutAppBarDemo()
}

