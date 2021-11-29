package com.example.lykkehjulet

import java.lang.StringBuilder
import kotlin.random.Random

class GameManager {

    private var lettersUsed: String = ""
    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private var lives: Int = 5



    fun startNewGame(): GameState{
        lettersUsed = ""
        lives = 5
        val randomIndex = Random.nextInt(0, GameConstants.words.size)
        wordToGuess = GameConstants.words[randomIndex]
        generateLetterBoxes(wordToGuess)
        return getGameState() // TODO implement this
    }

    fun generateLetterBoxes(word: String) {
        val sb = StringBuilder()
        word.forEach { char ->
            if (char == '-') {
                sb.append('-')
            } else {
                sb.append("_")
            }
        }
        underscoreWord = sb.toString()
    }

    fun play(letter: Char): GameState {

        if (lettersUsed.contains(letter)) {
            return GameState.Running(lettersUsed, underscoreWord)
        }

        lettersUsed += "$letter"
        val indexes = mutableListOf<Int>()

        wordToGuess.forEachIndexed{index, char ->
            if (char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = "" + underscoreWord
        indexes.forEach {index ->
            val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, letter) }
            finalUnderscoreWord = sb.toString()
        }

        if (indexes.isEmpty()) {
            lives--
        }

        underscoreWord = finalUnderscoreWord

        return getGameState()
    }


    fun getGameState(): GameState{
        if (underscoreWord.equals(wordToGuess, true)){
            return GameState.Won(wordToGuess)
        }

        if (lives == 0) {
            return GameState.Lost(wordToGuess)
        }

        return GameState.Running(lettersUsed, underscoreWord) // maybe add drawable
    }

    fun spinWheel() {
        val wheelIndex = Random.nextInt(0,1)
        when (wheelIndex) {
            0 -> lives++
            1 -> lives--
//            2 ->
//            3 ->
//            4 ->
//            5 ->
//            6 ->
//            7 ->
        }
    }


}