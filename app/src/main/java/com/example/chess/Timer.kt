package com.example.chess

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.chess.databinding.FragmentTimerBinding

class Timer : Fragment() {

    private lateinit var binding: FragmentTimerBinding
    private val cvm: ChessViewModel by activityViewModels()

    private lateinit var player1Timer: TextView
    private lateinit var player2Timer: TextView

    private lateinit var player1Move:TextView
    private lateinit var player2Move:TextView

    private lateinit var buttonTop: Button
    private lateinit var buttonBottom: Button

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

        buttonTop = binding.topButton
        buttonBottom = binding.bottomButton

        player1Move = binding.moveCountBottom
        player2Move = binding.moveCountTop

        pause = binding.pauseButton
        restart = binding.restartButton
        settings = binding.settingButton
        trackMove = binding.moveButton

        createTheme()

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

    fun createTheme() {

        if (cvm.getTheme().equals("Hokie")) {
            buttonTop.setBackgroundColor(Color.rgb(99,0,49))
            buttonBottom.setBackgroundColor(Color.rgb(207,69,32))

            player1Move.setTextColor(Color.rgb(99,0,49))
            player2Move.setTextColor(Color.rgb(207,69,32))

            player1Timer.setTextColor(Color.rgb(99,0,49))
            player2Timer.setTextColor(Color.rgb(207,69,32))
        }
        else if (cvm.getTheme().equals("Cavalier")) {

            buttonTop.setBackgroundColor(Color.rgb(229,114,0))
            buttonBottom.setBackgroundColor(Color.rgb(35,45,75))

            player1Move.setTextColor(Color.rgb(229,114,0))
            player2Move.setTextColor(Color.rgb(35,45,75))

            player1Timer.setTextColor(Color.rgb(229,114,0))
            player2Timer.setTextColor(Color.rgb(35,45,75))

        }
        else if (cvm.getTheme().equals("Duke Dog")) {

            buttonTop.setBackgroundColor(Color.rgb(69,0,132))
            buttonBottom.setBackgroundColor(Color.rgb(203,182,119))

            player1Timer.setBackgroundColor(Color.rgb(69,0,132))
            player2Timer.setBackgroundColor(Color.rgb(203,182,119))

            player1Timer.setTextColor(Color.rgb(69,0,132))
            player2Timer.setTextColor(Color.rgb(203,182,119))

        }
        else if (cvm.getTheme().equals("Patriots")) {

            buttonTop.setBackgroundColor(Color.rgb(30,98,56))
            buttonBottom.setBackgroundColor(Color.rgb(226,168,43))

            player1Timer.setBackgroundColor(Color.rgb(30,98,56))
            player2Timer.setBackgroundColor(Color.rgb(226,168,43))

            player1Timer.setTextColor(Color.rgb(30,98,56))
            player2Timer.setTextColor(Color.rgb(226,168,43))
        }
        else {
            player1Timer.setBackgroundColor(Color.WHITE)
            player2Timer.setBackgroundColor(Color.WHITE)
            player1Timer.setTextColor(Color.BLACK)
            player2Timer.setTextColor(Color.BLACK)
            buttonTop.setBackgroundColor(Color.GRAY)
            buttonBottom.setBackgroundColor(Color.GRAY)
        }

    }

}