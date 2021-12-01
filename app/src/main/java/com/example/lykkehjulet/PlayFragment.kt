package com.example.lykkehjulet


import android.app.GameManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.core.view.children
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lykkehjulet.databinding.FragmentPlayBinding
import kotlin.random.Random
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [PlayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayFragment : Fragment() {

    private val gameManager = GameManager()


    private var _binding : FragmentPlayBinding? = null
    private val binding get() = _binding!!

    lateinit var livesAdapter: LivesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_play, container, false)
        //val view = inflater.inflate(R.layout.fragment_play,container,false)
        _binding = FragmentPlayBinding.inflate(inflater,container,false)
        val view = binding.root



        livesAdapter = LivesAdapter(gameManager.getLivesL())
        binding.recyclerViewLives.adapter = livesAdapter
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewLives.layoutManager = layoutManager



        val gameState = gameManager.startNewGame()
        notifyChange(gameState)



        binding.categoryTextView.text = gameManager.category.name



        // used for the keyboard
        binding.lettersLayout.children.forEach { letterView ->
            if (letterView is TextView) {
                letterView.setOnClickListener {
                    val gameState = gameManager.play((letterView).text[0])
                    gameManager.keyboard = false
                    notifyChange(gameState)
                    letterView.visibility = View.GONE
                }
            }
        }

        binding.spinButton.setOnClickListener {
            gameManager.spinWheel()
            notifyChange(gameManager.getGameState())
        }


        return view
    }





    private fun notifyChange(gameState: GameState) {
        when (gameState) {
            is GameState.Lost -> showGameLost(gameState.wordToGuess) // TODO make this work
            is GameState.Running -> {
                binding.wordTextview.text = gameState.underscoreWord // TODO make this work
                binding.letterUsedTextView.text = "letters used: ${gameState.letterUsed}" // TODO make this work
                binding.score.text = "Score: ${gameManager.score}"
                //binding.lives.text = "Lives: ${gameManager.getLives()}"
                binding.result.text = gameManager.resultMSG
                livesAdapter.notifyDataSetChanged()
                if (gameManager.keyboard) {
                    binding.lettersLayout.visibility = View.VISIBLE
                    binding.spinButton.visibility = View.INVISIBLE
                } else {
                    binding.lettersLayout.visibility = View.INVISIBLE
                    binding.spinButton.visibility = View.VISIBLE
                }
            }
            is GameState.Won -> showGameWon(gameState.wordToGuess) // TODO make this work
        }
    }


    private fun showGameLost(wordToGuess: String) {
        view?.let { Navigation.findNavController(it).navigate(R.id.action_playFragment_to_lostFragment) }
    }
    private fun showGameWon(wordToGuess: String) {
        view?.let { Navigation.findNavController(it).navigate(R.id.action_playFragment_to_wonFragment) }
    }



}