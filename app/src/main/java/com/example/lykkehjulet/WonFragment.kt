package com.example.lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lykkehjulet.databinding.FragmentLostBinding
import com.example.lykkehjulet.databinding.FragmentWonBinding

/**
 * A simple [Fragment] subclass.
 * Use the [WonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WonFragment : Fragment() {


    private var _binding : FragmentWonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWonBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.newGameButtonWon.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_wonFragment_to_playFragment) }

        return view
    }

}