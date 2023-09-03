package com.jazzy.mycomposegame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.getImageByFileName

enum class Side {
    FRONT,
    BACK
}
class CardData(
    val width: Float,
    val height: Float,
    val xOff : Float,
    val yOff : Float,
    val type:Int = 0,
    side: Side = Side.BACK
) : GameObject() {
    override var size: Float = 1f

    var side by mutableStateOf(side)

    fun getImageResource(): ImageResource =
        if (side == Side.FRONT)
        when (type) {
            0 -> MR.images.apple
            1 -> MR.images.mango
            2 -> MR.images.avocado
            3 -> MR.images.grape
            4 -> MR.images.lemon
            5 -> MR.images.peach
            6 -> MR.images.strewberry
            7 -> MR.images.watermelon
            else -> MR.images.watermelon
        } else
            MR.images.question

}