package com.example.lykkehjulet.ViewModel

sealed class GameState {
    class Running(
        val letterUsed: String,
        val underscoreWord: String,
        ): GameState()
    class Lost(val word: String): GameState()
    class Won(val score: Int): GameState()
}