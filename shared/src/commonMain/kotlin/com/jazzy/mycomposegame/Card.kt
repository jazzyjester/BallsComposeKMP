package com.jazzy.mycomposegame

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun Card(cardData: CardData, game: Game) {
    val width = cardData.width.dp
    val height = cardData.height.dp
    val xOff = cardData.xOff.dp
    val yOff = cardData.yOff.dp
    Box(
        Modifier
            .offset(xOff, yOff)
            .size(width, height)
            .paint(
                painter = painterResource(cardData.getImageResource()),
                contentScale = ContentScale.Fit
            )
            .clickable {
                game.cardClicked(cardData)
            }
    )
}