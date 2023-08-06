package com.jazzy.mycomposegame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.romainguy.kotlin.math.Float2
import dev.romainguy.kotlin.math.length


abstract class GameObject(
    speed: Float = 0.0F,
    angle: Float = 0.0F,
    position: Float2 = Float2()
) {
    companion object {
        val UNIT_X = Float2(1.0f, 0.0f)
    }

    var speed by mutableStateOf(speed)
    var angle by mutableStateOf(angle)
    var position by mutableStateOf(position)
    var movementVector
        get() = (UNIT_X.times(speed)).rotate(angle)
        set(value) {
            speed = length(value)
            angle = value.angle()
        }
    abstract val size: Float // Diameter

    open fun update(realDelta: Float, game: Game) {
        val obj = this
        val velocity = movementVector.times(realDelta)
        obj.position = obj.position.plus(velocity)
    }
}
