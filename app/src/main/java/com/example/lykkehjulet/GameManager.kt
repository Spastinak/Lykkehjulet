package com.example.lykkehjulet

import android.view.View
import com.example.lykkehjulet.databinding.FragmentPlayBinding
import java.lang.StringBuilder
import kotlin.random.Random

class GameManager {

    private var lettersUsed: String = ""
    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    var resultMSG: String = ""
    private var lives: Int = 5
    var score: Int = 0
    var keyboard: Boolean = false


    private var _binding : FragmentPlayBinding? = null
    private val binding get() = _binding!!

    fun getLives(): Int {
        return lives
    }

    fun startNewGame(): GameState{
        lettersUsed = ""
        lives = 5
        val randomIndex = Random.nextInt(0, GameConstants.words.size)
        wordToGuess = GameConstants.words[randomIndex]
        generateLetterBoxes(wordToGuess)
        return getGameState() // TODO implement this
    }

    private fun generateLetterBoxes(word: String) {
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
        val wheelIndex = Random.nextInt(0,7)
        when (wheelIndex) {
            0 -> {
                lives++
                resultMSG = "Extra turn"
            }
            1 -> {
                lives--
                resultMSG = "Miss Turn"
            }
            2 -> {
                score = 0
                resultMSG = "Bankrupt"
            }
            3 -> {
                score += 25
                resultMSG = "25"
            }
            4 -> {
                score += 50
                resultMSG = "50"
            }
            5 -> {
                score += 100
                resultMSG = "100"
            }
            6 -> {
                score += 1000
                resultMSG = "1000"
            }
            7 -> {
                score += 1500
                resultMSG = "1500"
            }
        }
    }


}