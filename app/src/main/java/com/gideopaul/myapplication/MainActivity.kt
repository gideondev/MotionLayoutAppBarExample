package com.gideopaul.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

    val progress by animateFloatAsState(
        targetValue = if (scrollState.value > 150) 1f else 0f,
        tween(500, easing = LinearOutSlowInEasing)
    )

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
            progress = progress,
            backgroundColor = Color(0xFF375857)
        )
    }
}

@Preview
@Composable
fun PreviewMotionLayoutAppBarDemo() {
    MotionLayoutAppBarDemo()
}
