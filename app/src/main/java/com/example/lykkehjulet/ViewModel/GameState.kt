package com.example.lykkehjulet.ViewModel

sealed class GameState {
    class Running(
        val letterUsed: String,
        val underscoreWord: String,
        ): GameState()
    object Lost : GameState()
    object Won : GameState()
}