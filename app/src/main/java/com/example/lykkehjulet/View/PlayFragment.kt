package com.example.lykkehjulet.View


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.core.view.children
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lykkehjulet.ViewModel.GameManager
import com.example.lykkehjulet.ViewModel.GameState
import com.example.lykkehjulet.LivesAdapter
import com.example.lykkehjulet.R
import com.example.lykkehjulet.databinding.FragmentPlayBinding
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


        // Render the lives images, using recycler view
        livesAdapter = LivesAdapter(gameManager.getLives())
        binding.recyclerViewLives.adapter = livesAdapter
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewLives.layoutManager = layoutManager



        // Start a new game and update the PlayFragment
        val gameState = gameManager.startNewGame()
        notifyChange(gameState)


        // write the the category name in the text view
        binding.categoryTextView.text = gameManager.category.name


        /**
         * used for the keyboard
         * render all the letters and set an onClickListener on them, and removes the clicked letter
         */
        binding.lettersLayout.children.forEach { letterView ->
            if (letterView is TextView) {
                letterView.setOnClickListener {
                    val gameState = gameManager.guessOnLetter((letterView).text[0])
                    gameManager.keyboard = false // removes the keyboard
                    notifyChange(gameState)
                    letterView.visibility = View.GONE
                }
            }
        }

        // Make the button spin the wheel
        binding.spinButton.setOnClickListener {
            gameManager.spinWheel()
            notifyChange(gameManager.getGameState())
        }


        return view
    }


    /**
     * Checks if the game has been won, lost or is still running. If running it will update the UI, else navigate to lost or won fragment
     */
    private fun notifyChange(gameState: GameState) {
        when (gameState) {
            is GameState.Lost -> showGameLost()
            is GameState.Running -> { // update playFragment
                binding.wordTextview.text = gameState.underscoreWord
                binding.letterUsedTextView.text = "letters used: ${gameState.letterUsed}"
                binding.score.text = "Score: ${gameManager.score}"
                binding.result.text = gameManager.resultMSG
                livesAdapter.notifyDataSetChanged() // update the rendered lives
                if (gameManager.keyboard) { // Render ether the keyboard or the spin button
                    binding.lettersLayout.visibility = View.VISIBLE
                    binding.spinButton.visibility = View.INVISIBLE
                } else {
                    binding.lettersLayout.visibility = View.INVISIBLE
                    binding.spinButton.visibility = View.VISIBLE
                }
            }
            is GameState.Won -> showGameWon()
        }
    }


    // navigate to lostFragment
    private fun showGameLost() {
        view?.let { Navigation.findNavController(it).navigate(R.id.action_playFragment_to_lostFragment) }
    }

    // navigate to wonFragment
    private fun showGameWon() {
        view?.let { Navigation.findNavController(it).navigate(R.id.action_playFragment_to_wonFragment) }
    }



}