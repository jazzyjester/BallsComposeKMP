package com.jazzy.mycomposegame

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.romainguy.kotlin.math.Float2
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random


val GameObject.xOffset: Dp get() = position.x.dp
val GameObject.yOffset: Dp get() = position.y.dp

inline val Float.asRadians: Float get() = this * 0.017453292f
inline val Float.asDegrees: Float get() = this * 57.29578f

fun Float2.angle(): Float {
    val rawAngle = atan2(y = this.y, x = this.x)
    return ((rawAngle / PI) * 180F).toFloat()
}

fun Float2.rotate(degrees: Float, origin: Float2 = Float2()): Float2 {
    val p = this - origin
    val a = degrees.asRadians

    val w = Float2(
        p.x * cos(a) - p.y * sin(a),
        p.y * cos(a) + p.x * sin(a)
    )

    return w + origin
}

fun random(min: Int, max: Int): Float {
    require(min < max) { "Invalid range [$min, $max]" }
    return min + Random.nextFloat() * (max - min)
}

