package com.example.calvin_wu_assignment_2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel : ViewModel() {
    val player1 = Beetle()
    val player2 = Beetle()
    var isPlayer1Turn = true
    var player1Score = MutableLiveData(0)
    var player2Score = MutableLiveData(0)

    fun rollDice(): RollResult {
        val roll = Random.nextInt(1,7)
        val beetle = if (isPlayer1Turn) player1 else player2
        val who = if (isPlayer1Turn) "Player 1" else "Player 2"

        val actionResult = beetle.applyRoll(roll)

        if (actionResult.contains("added", ignoreCase = true)) {
            if (isPlayer1Turn) {
                player1Score.value = (player1Score.value ?:0) + 1
            } else {
                player2Score.value = (player2Score.value ?:0) + 1
            }
        }

        val winner = checkWinner()

        isPlayer1Turn = !isPlayer1Turn

        return RollResult(
            roller = who,
            roll = roll,
            resultMessage = "$who rolled a $roll. $actionResult",
            winner = winner
        )
    }

    private fun checkWinner(): String? {
        return when {
            player1.isComplete() -> "Player 1"
            player2.isComplete() -> "Player 2"
            else -> null
        }
    }

    fun resetGame() {
        player1.reset()
        player2.reset()
        isPlayer1Turn = true
        player1Score.value = 0
        player2Score.value = 0
    }
}

data class RollResult(
    val roller: String,
    val roll: Int,
    val resultMessage: String,
    val winner: String? = null
)