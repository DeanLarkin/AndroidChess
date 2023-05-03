package com.example.chess

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.chess.databinding.FragmentTimerBinding
import java.util.*

class Timer : Fragment() {

    private lateinit var binding: FragmentTimerBinding
    private val cvm: ChessViewModel by activityViewModels()

    private lateinit var player1Timer: TextView
    private lateinit var player2Timer: TextView

    private lateinit var player1Move:TextView
    private lateinit var player2Move:TextView

    private lateinit var pause: Button
    private lateinit var settings:Button
    private lateinit var trackMove:Button
    private lateinit var restart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTimerBinding.inflate(layoutInflater, container, false)

        player1Timer = binding.timerBottom
        player2Timer = binding.timerTop

        player1Move = binding.moveCountBottom
        player2Move = binding.moveCountTop

        pause = binding.pauseButton
        restart = binding.restartButton
        settings = binding.settingButton
        trackMove = binding.moveButton



        settings.setOnClickListener {
            cvm.post("Navigated to settings fragment")
            view?.findNavController()?.navigate(R.id.action_timer_to_home2)
        }

        // Initialize a flag to keep track of whether the pause button is clicked
        var isPaused = false

// Set up the observer for countLive inside onCreateView or onViewCreated
        cvm.countLive.observe(viewLifecycleOwner, Observer { count ->
            // Check if the pause button is clicked
            if (isPaused) {
                if (count >= 0 && cvm.min >= 0) {
                    if (count < 10 && cvm.min < 10) {
                        player1Timer?.text = "0${cvm.min}:0${count}"
                    } else if (count >= 10 && cvm.min < 10) {
                        player1Timer?.text = "0${cvm.min}:${count}"
                    } else if (count < 10 && cvm.min >= 10) {
                        player1Timer?.text = "${cvm.min}:0${count}"
                    } else {
                        player1Timer?.text = "${cvm.min}:${count}"
                    }
                } else {
                    // countdown finished, do something
                    player1Timer?.text = "00:00"
                }
            }
        })

// Set up the click listener for the pause button
        pause.setOnClickListener{
            // Toggle the isPaused flag between true and false

            isPaused = !isPaused
            // Update the text of the pause button based on the current state of isPaused
            pause.text = if (isPaused) "Pause"  else "Play"
            if (isPaused)
                cvm.post("Paused timer")
            else
                cvm.post("Resumed timer")

            // Update the countLive value to trigger the observer
           // cvm.countLive.value = cvm.countLive.value
        }

        trackMove.setOnClickListener {
            cvm.post("Navigated to Tracking move fragment")

            view?.findNavController()?.navigate(R.id.action_timer_to_moveLayout)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}