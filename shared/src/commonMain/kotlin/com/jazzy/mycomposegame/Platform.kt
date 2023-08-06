package com.jazzy.mycomposegame

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform