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

    private lateinit var player1Timer: TextView
    private lateinit var player2Timer: TextView

    private lateinit var player1Move:TextView
    private lateinit var player2Move:TextView

    private lateinit var pause: ImageButton
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

        player1Timer = binding.timerBottom
        player2Timer = binding.timerTop

        player1Move = binding.moveCountBottom
        player2Move = binding.moveCountTop

        pause = binding.pauseButton
        restart = binding.restartButton
        settings = binding.settingButton
        trackMove = binding.moveButton



        settings.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_timer_to_home2)
        }
        trackMove.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_timer_to_moveLayout)
        }




        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }


}