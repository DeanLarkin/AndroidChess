package com.example.chess

import android.graphics.Color
import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.chess.databinding.FragmentTimerBinding

class Timer : Fragment() {

    private lateinit var binding: FragmentTimerBinding
    private val cvm: ChessViewModel by activityViewModels()

    private lateinit var player1Timer: TextView
    private lateinit var player2Timer: TextView

    private lateinit var player1Move: TextView
    private lateinit var player2Move: TextView

    private lateinit var buttonTop: Button
    private lateinit var buttonBottom: Button
    private var countDownTimer1: CountDownTimer? = null
    private var countDownTimer2: CountDownTimer? = null

    private lateinit var moves: Button
    private lateinit var settings: Button
    private lateinit var trackMove: Button
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

        moves = binding.moveDisplayButton
        restart = binding.restartButton
        settings = binding.settingButton
        trackMove = binding.moveButton

        var isTimerRunning = false

        binding.timerBottom.setOnClickListener {
            if (countDownTimer1 == null) {
                // Timer is not running. Start it.
                cvm.player = 1
                cvm.setMoveCount()
                player1Move.text = "Move Count: " + cvm.getMoveCount().toString()
                countDownTimer1 = object : CountDownTimer(cvm.getTimerData1()!!, 1000) {
                    override fun onTick(p0: Long) {
                        val f: NumberFormat = DecimalFormat("00")
                        val min: Long = p0 / 60000 % 60
                        val sec: Long = p0 / 1000 % 60
                        binding.timerBottom.setText(
                            (f.format(min)).toString() + ":" + f.format(
                                sec
                            )
                        )
                        cvm.setTimerData1(p0)
                    }

                    override fun onFinish() {
                        binding.timerBottom.setText("00:00:00");
                    }

                }.start()

                // Pause the other timer, if running
                if (countDownTimer2 != null) {
                    countDownTimer2!!.cancel()
                    countDownTimer2 = null
                }
            } else {
                // Timer is running. Pause it.
                countDownTimer1!!.cancel()
                countDownTimer1 = null

                // Start the other timer, if not running
                cvm.player = 2
                cvm.setMoveCount()
                player2Move.text = "Move Count: " + cvm.getMoveCount().toString()
                if (countDownTimer2 == null) {
                    countDownTimer2 = object : CountDownTimer(cvm.getTimerData2()!!, 1000) {
                        override fun onTick(p0: Long) {
                            val f: NumberFormat = DecimalFormat("00")
                            val min: Long = p0 / 60000 % 60
                            val sec: Long = p0 / 1000 % 60
                            binding.timerTop.setText(
                                (f.format(min)).toString() + ":" + f.format(
                                    sec
                                )
                            )
                            cvm.setTimerData2(p0)
                        }

                        override fun onFinish() {
                            binding.timerTop.setText("00:00:00");
                        }

                    }.start()
                }
            }
        }

        binding.timerTop.setOnClickListener {
            cvm.player = 1
            cvm.setMoveCount()
            player1Move.text = "Move Count: " + cvm.getMoveCount().toString()
            if (countDownTimer2 == null) {
                // Timer is not running. Start it.
                countDownTimer2 = object : CountDownTimer(cvm.getTimerData2()!!, 1000) {
                    override fun onTick(p0: Long) {
                        val f: NumberFormat = DecimalFormat("00")
                        val min: Long = p0 / 60000 % 60
                        val sec: Long = p0 / 1000 % 60
                        binding.timerTop.setText(
                            (f.format(min)).toString() + ":" + f.format(
                                sec
                            )
                        )
                        cvm.setTimerData2(p0)
                    }

                    override fun onFinish() {
                        binding.timerTop.setText("00:00:00");
                    }

                }.start()

                // Pause the other timer, if running
                if (countDownTimer1 != null) {
                    countDownTimer1!!.cancel()
                    countDownTimer1 = null
                }
            } else {
                // Timer is running. Pause it.
                countDownTimer2!!.cancel()
                countDownTimer2 = null

                cvm.player = 2
                cvm.setMoveCount()
                player2Move.text = "Move Count: " + cvm.getMoveCount().toString()
                // Start the other timer, if not running
                if (countDownTimer1 == null) {
                    countDownTimer1 = object : CountDownTimer(cvm.getTimerData1()!!, 1000) {
                        override fun onTick(p0: Long) {
                            val f: NumberFormat = DecimalFormat("00")
                            val min: Long = p0 / 60000 % 60
                            val sec: Long = p0 / 1000 % 60
                            binding.timerBottom.setText(
                                (f.format(min)).toString() + ":" + f.format(
                                    sec
                                )
                            )
                            cvm.setTimerData1(p0)
                        }

                        override fun onFinish() {
                            binding.timerBottom.setText("00:00:00");
                        }

                    }.start()
                }
            }
        }

        settings.setOnClickListener {
            cvm.post("Navigated to settings fragment")
            view?.findNavController()?.navigate(R.id.action_timer_to_home2)
        }

        createTheme()

        moves.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_timer_to_moveDisplayFragment)
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
            buttonTop.setBackgroundColor(Color.rgb(99, 0, 49))
            buttonBottom.setBackgroundColor(Color.rgb(207, 69, 32))

            player1Move.setTextColor(Color.rgb(99, 0, 49))
            player2Move.setTextColor(Color.rgb(207, 69, 32))

            player1Timer.setTextColor(Color.rgb(99, 0, 49))
            player2Timer.setTextColor(Color.rgb(207, 69, 32))
        } else if (cvm.getTheme().equals("Cavalier")) {

            buttonTop.setBackgroundColor(Color.rgb(229, 114, 0))
            buttonBottom.setBackgroundColor(Color.rgb(35, 45, 75))

            player1Move.setTextColor(Color.rgb(229, 114, 0))
            player2Move.setTextColor(Color.rgb(35, 45, 75))

            player1Timer.setTextColor(Color.rgb(229, 114, 0))
            player2Timer.setTextColor(Color.rgb(35, 45, 75))

        } else if (cvm.getTheme().equals("Duke Dog")) {

            buttonTop.setBackgroundColor(Color.rgb(69, 0, 132))
            buttonBottom.setBackgroundColor(Color.rgb(203, 182, 119))

            player1Move.setTextColor(Color.rgb(69, 0, 132))
            player2Move.setTextColor(Color.rgb(203, 182, 119))

            player1Timer.setTextColor(Color.rgb(69, 0, 132))
            player2Timer.setTextColor(Color.rgb(203, 182, 119))

        } else if (cvm.getTheme().equals("Patriots")) {

            buttonTop.setBackgroundColor(Color.rgb(30, 98, 56))
            buttonBottom.setBackgroundColor(Color.rgb(226, 168, 43))

            player1Move.setTextColor(Color.rgb(30, 98, 56))
            player2Move.setTextColor(Color.rgb(226, 168, 43))

            player1Timer.setTextColor(Color.rgb(30, 98, 56))
            player2Timer.setTextColor(Color.rgb(226, 168, 43))
        } else {
            player1Timer.setBackgroundColor(Color.WHITE)
            player2Timer.setBackgroundColor(Color.WHITE)
            player1Timer.setTextColor(Color.BLACK)
            player2Timer.setTextColor(Color.BLACK)
            buttonTop.setBackgroundColor(Color.GRAY)
            buttonBottom.setBackgroundColor(Color.GRAY)
        }

    }

}