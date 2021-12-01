package com.example.lykkehjulet.ViewModel

import com.example.lykkehjulet.Category
import java.lang.StringBuilder
import kotlin.random.Random

class GameManager {

    private var lettersUsed: String = ""
    private lateinit var hiddenWord: String
    var resultMSG: String = ""
    var score: Int = 0
    var keyboard: Boolean = false
    private var scoreMultiplyer: Int = 0
    private var lives : MutableList<String> = mutableListOf(" "," "," "," "," ")
    var category = Category.values().random()
    private var word = category.words.random()



    // Used to start a new game
    fun startNewGame(): GameState {
        lettersUsed = ""

        generateHiddenLetters(word)
        return getGameState()
    }

    // generate the hidden letters represented by an underscore, spaces are represented by "-"
    private fun generateHiddenLetters(word: String) {
        val stringBuilder = StringBuilder()
        word.forEach { char ->
            if (char == '-') {
                stringBuilder.append('-')
            } else {
                stringBuilder.append("_")
            }
        }
        hiddenWord = stringBuilder.toString()
    }

    /**
     * Function for guessing on a letter, if guessed right add the letter to the hiddenWord, and add to the score.
     * Else loose a life
     */
    fun guessOnLetter(letter: Char): GameState {


        lettersUsed += "$letter"
        val indexes = mutableListOf<Int>()

        // add the letter to index if it occurs in the word
        word.forEachIndexed{ index, char ->
            if (char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        // add the letter to the finalHiddenWord, if it occurs in the word
        var finalHiddenWord = "" + hiddenWord
        indexes.forEach {index ->
            val stringBuilder = StringBuilder(finalHiddenWord).also { it.setCharAt(index, letter) }
            finalHiddenWord = stringBuilder.toString()
            score += scoreMultiplyer
        }

        // if the guessed letter did not occur loose a life
        if (indexes.isEmpty()) {
            lives.removeLast()
        }

        // update the hidden word
        hiddenWord = finalHiddenWord
        scoreMultiplyer = 0

        return getGameState()
    }

    // Checks if the game has been won, lost or is still running
    fun getGameState(): GameState {
        if (hiddenWord.equals(word, true)){
            return GameState.Won(score)
        }

        if (lives.isEmpty()) {
            return GameState.Lost(word)
        }

        return GameState.Running(lettersUsed, hiddenWord)
    }

    fun getLives() : MutableList<String> {
        return lives
    }


    /**
     * used for the spin wheel button
     * draws a random index and execute the event. if it is bigger than 3 activate the keyboard, allowing to guess in a letter
     */
    fun spinWheel() {
        val wheelIndex = Random.nextInt(0,7)
        if (wheelIndex >= 3) {
            keyboard = true
        }
        when (wheelIndex) {
            0 -> {
                lives.add(" ")
                resultMSG = "Extra turn! \nYou gain a life!"
            }
            1 -> {
                lives.removeLast()
                resultMSG = "Miss Turn! \nYou loose a life!"
            }
            2 -> {
                score = 0
                resultMSG = "Bankrupt!\nYou loose all points!"
            }
            3 -> {
                scoreMultiplyer += 25
                resultMSG = "25!\nEarn points on occurrences."
            }
            4 -> {
                scoreMultiplyer += 50
                resultMSG = "50!\nEarn points on occurrences."
            }
            5 -> {
                scoreMultiplyer += 100
                resultMSG = "100!\nEarn points on occurrences."
            }
            6 -> {
                scoreMultiplyer += 1000
                resultMSG = "1000!\nEarn points on occurrences."
            }
            7 -> {
                scoreMultiplyer += 1500
                resultMSG = "1500!\nEarn points on occurrences."
            }
        }
    }


}