package com.example.chess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.chess.databinding.FragmentHomeBinding

class Home : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var play:Button
    private val cvm: ChessViewModel by activityViewModels()
    private lateinit var timeSpinner: Spinner
    private lateinit var themeSpinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        play = binding.playButton
        timeSpinner = binding.timeSpinner
        themeSpinner = binding.themeSpinner

        play.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_home2_to_timer)

            cvm.count1 = timeSpinner.selectedItemPosition
            cvm.count2 = timeSpinner.selectedItemPosition
            cvm.setTheme(themeSpinner.selectedItem.toString())
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}