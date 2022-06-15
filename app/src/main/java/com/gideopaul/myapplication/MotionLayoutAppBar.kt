package com.gideopaul.myapplication

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
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
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene

val motionSceneJson = """
{
  "ConstraintSets": {
    "start": {
      // Navigation icon will be at the start with a padding of 16dp.
      "navigation_icon": {
        "start": ["parent", "start", 16],
        "top": ["parent", "top", 16],
        "bottom": ["parent", "bottom", 16],
      },
      
      // Title will be beside the navigation icon after 16dp spacing
      "title": {
        "start": ["navigation_icon", "end", 16],
        "top": ["parent", "top", 16]
      },
      
      // Subtitle will be directly below the title and it's start will be aligned to title.
      "subtitle": {
        "start": ["title", "start", 0],
        "top": ["title", "bottom", 4],
        "bottom": ["parent", "bottom", 16],
      },
      
      // Height and width are set to spread, that's equivalent to match_parent
      "background": {
        "width": "spread",
        "height":  "spread",
        "start": ["parent", "start"],
        "end": ["parent", "end"],
        "top": ["parent", "top"],
        "bottom": ["parent", "bottom"],
      }
    },
    "end": {
     "navigation_icon": {
        "start": ["parent", "start", 16],
        "top": ["parent", "top", 16]
      },
      "title": {
        "start": ["parent", "start", 32],
        "top": ["navigation_icon", "bottom", 16],
      },
      "subtitle": {
        "start": ["title", "start", 0],
        "top": ["title", "bottom", 16],
        "bottom": ["parent", "bottom", 16],
      },
      "background": {
        "width": "spread",
        "height":  "spread",
        "start": ["parent", "start"],
        "end": ["parent", "end"],
        "top": ["parent", "top"],
        "bottom": ["parent", "bottom"],
      }
    }
  }
}
"""


@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionLayoutAppBar(
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onPrimary,
    progress: Float = 0.0f
) {
    MotionLayout(
        modifier = Modifier.fillMaxWidth(),
        motionScene = MotionScene(motionSceneJson),
        progress = progress
    ) {
        Box(
            modifier = Modifier
                .layoutId("background")
                .background(backgroundColor)
        )

        IconButton(
            modifier = Modifier.layoutId("navigation_icon"),
            onClick = { }
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


@Preview
@Composable
fun PreviewMotionLayoutAppBar() {
    val motionLayoutProgress = remember { Animatable(0.0f) }

    LaunchedEffect(Unit) {
        motionLayoutProgress.animateTo(
            1.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1500, easing = LinearOutSlowInEasing)
            )
        )
    }

    MotionLayoutAppBar(
        progress = motionLayoutProgress.value
    )
}