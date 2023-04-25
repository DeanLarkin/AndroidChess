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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
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

        play.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_home2_to_timer)

            cvm.count = timeSpinner.selectedItemPosition
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}