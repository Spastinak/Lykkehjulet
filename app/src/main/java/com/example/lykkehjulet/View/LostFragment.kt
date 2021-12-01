package com.example.lykkehjulet.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lykkehjulet.R
import com.example.lykkehjulet.databinding.FragmentLostBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LostFragment : Fragment() {

    private var _binding : FragmentLostBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_lost, container, false)
        //val view = inflater.inflate(R.layout.fragment_lost, container, false)

        _binding = FragmentLostBinding.inflate(inflater,container,false)

        val view = binding.root


        binding.newGameButtonLost.setOnClickListener {  Navigation.findNavController(view).navigate(R.id.action_lostFragment_to_playFragment )}


        return view
    }

}