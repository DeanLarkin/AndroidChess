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
import com.example.chess.databinding.FragmentTimerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Timer.newInstance] factory method to
 * create an instance of this fragment.
 */
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
    private lateinit var restart:ImageButton

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
        //restart = binding.restartButton
        settings = binding.settingButton2
        trackMove = binding.moveButton



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

}