package com.example.lykkehjulet

sealed class GameState {
    class Running(
        val letterUsed: String,
        val underscoreWord: String,
        //val drawable: Int maybe add this
        ): GameState()
    class Lost(val wordToGuess: String): GameState()
    class Won(val score: Int): GameState()
}