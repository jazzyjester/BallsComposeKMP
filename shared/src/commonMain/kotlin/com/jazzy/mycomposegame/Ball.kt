package com.jazzy.mycomposegame

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun Ball(ballData: BallData) {
    val ballSize = ballData.size.dp
    Box(
        Modifier
            .offset(ballData.xOffset, ballData.yOffset)
            .size(ballSize)
            .clip(CircleShape)
            .background(ballData.color)
            .paint(
                painter = painterResource(MR.images.ball),
                contentScale = ContentScale.FillBounds
            )
            .clickable { ballData.isEnabled = !ballData.isEnabled }
    )
}