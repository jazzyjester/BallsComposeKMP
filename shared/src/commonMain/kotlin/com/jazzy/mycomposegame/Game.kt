package com.jazzy.mycomposegame


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

enum class GameState {
    STOPPED, RUNNING
}

class Game {
    private var prevTime = 0L

    var gameObjects = mutableStateListOf<GameObject>()
    private var gameState by mutableStateOf(GameState.RUNNING)
    private var totalTime by mutableStateOf(0L)

    private var firstTurnedCard: CardData? = null
    private var secondTurnedCard: CardData? = null
    private var timer: Float = 0f

    fun startGame() {
        gameObjects.clear()

        val totalX = 4
        val totalY = 4
        val tileXSize = (width.value / totalX)
        val tileYSize = (height.value / totalY)
        val off = 10f

        val shuffledList = listOf(
            (0 until totalX + totalY).toList(),
            (0 until totalX + totalY).toList()
        )
            .flatten().shuffled().toMutableList()

        repeat(totalX) { x ->
            repeat(totalY) { y ->
                gameObjects.add(
                    CardData(
                        width = tileXSize - off,
                        height = tileYSize - off,
                        xOff = (tileXSize) * x + off / 2,
                        yOff = (tileYSize) * y + off / 2,
                        type = shuffledList.removeAt(0)
                    )
                )
            }
        }

        gameState = GameState.RUNNING
        totalTime = 0L
    }

    fun update(time: Long) {
        val delta = time - prevTime
        val floatDelta = (delta / 1E8).toFloat()
        prevTime = time

//        if (gameState == GameState.STOPPED) return

        for (gameObject in gameObjects) {
            gameObject.update(floatDelta, this)
        }

        totalTime += delta

        if (timer > 0) {
            timer -= delta
            if (timer <= 0) {
                timerStopped()
            }
        }

    }

    private fun timerStopped() {
        checkCards()
    }

    private fun isFirstCardTurned() = firstTurnedCard != null

    private fun start1SecTimer() {
        timer = 1E9f
    }

    fun cardClicked(cardData: CardData) {
        cardData.side = Side.FRONT

        if (!isFirstCardTurned()) {
            firstTurnedCard = cardData
        } else {
            secondTurnedCard = cardData
            start1SecTimer()
        }
    }

    private fun checkCards() {
        if (firstTurnedCard?.type == secondTurnedCard?.type) {
            // need to remove them both
            gameObjects.remove(firstTurnedCard!!)
            gameObjects.remove(secondTurnedCard!!)

            if (gameObjects.isEmpty()) {
                // start over
                startGame()
            }
        } else {
            firstTurnedCard?.side = Side.BACK
            secondTurnedCard?.side = Side.BACK
        }

        firstTurnedCard = null
        secondTurnedCard = null

    }

    var width by mutableStateOf(0.dp)
    var height by mutableStateOf(0.dp)
}