package com.jazzy.mycomposegame

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.romainguy.kotlin.math.Float2

class BallData(
    ballSize: Float = 20f,
    val color: Color = Color.Red,
    var isEnabled: Boolean = true
) :
    GameObject() {
    override var size: Float = ballSize
    override fun update(realDelta: Float, game: Game) {

        if (!isEnabled) return

        super.update(realDelta, game)

        if (xOffset > game.width - size.dp || xOffset < 0.dp) {
            movementVector = movementVector.times(Float2(-1f, 1f))
            if (xOffset < 0.dp) position.x = 0f
            if (xOffset > game.width - size.dp) position.x = game.width.value - size
        } else if (yOffset > game.height - size.dp || yOffset < 0.dp) {
            movementVector = movementVector.times(Float2(1f, -1f))

            if (yOffset < 0.dp) position.y = 0f
            if (yOffset > game.height - size.dp) position.y = game.height.value - size
        }
    }

}
