package com.gideopaul.myapplication

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MotionLayoutAppBar() {
    IconButton(
        modifier = Modifier.layoutId("navigation_icon"),
        onClick = { }
    ) {
        Icon(Icons.Default.ArrowBack, "Back Button")
    }

    Text(
        modifier = Modifier.layoutId("title"),
        text = "Title"
    )

    Text(
        modifier = Modifier.layoutId("subtitle"),
        text = "Subtitle"
    )
}


@Preview
@Composable
fun PreviewMotionLayoutAppBar() {
    MotionLayoutAppBar()
}