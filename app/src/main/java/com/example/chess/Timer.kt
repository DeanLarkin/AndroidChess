package com.example.chess

import android.graphics.Color
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
            view?.findNavController()?.navigate(R.id.action_timer_to_home2)
        }
        pause.setOnClickListener{

            cvm.countLive.observe(viewLifecycleOwner, Observer {
                if (cvm.count!! >= 0 && cvm.min >= 0) {
                    if (cvm.count!! < 10 && cvm.min < 10) {
                        player1Timer?.text = "0${cvm.min}:0${cvm.count}"
                    } else if (cvm.count!! >= 10 && cvm.min < 10) {
                        player1Timer?.text = "0${cvm.min}:${cvm.count}"
                    } else if (cvm.count!! < 10 && cvm.min >= 10) {
                        player1Timer?.text = "${cvm.min}:0${cvm.count}"
                    } else {
                        player1Timer?.text = "${cvm.min}:${cvm.count}"
                    }
                } else {
                    // countdown finished, do something
                    player1Timer?.text = "00:00"
                }
            })


//            cvm.countLive.observe(viewLifecycleOwner, Observer {
//                val count = 59 - cvm.count!!  // subtract count from 59 to get the countdown value
//                val min = cvm.min
//                if (count < 10 && min < 10) {
//                    player1Timer?.text = "0$min:0$count"  // format the countdown value with leading zeros if necessary
//                    println("0$min:0$count")
//                } else if (count < 0 && min >= 10) {
//                    player1Timer?.text = "$min:00"  // if count goes below 0, display 00 for seconds
//                } else if (count >= 10 && min >= 10) {
//                    player1Timer?.text = "$min:$count"
//                } else {
//                    player1Timer?.text = "0$min:$count"
//                }
//            })

        }

        trackMove.setOnClickListener {

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