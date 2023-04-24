package com.example.chess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.chess.databinding.FragmentMoveLayoutBinding

class MoveLayout : Fragment() {

    private lateinit var binding: FragmentMoveLayoutBinding
    private val cvm: ChessViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding = FragmentMoveLayoutBinding.inflate(layoutInflater, container, false)
        var pieceName: String = ""
        var positionLetter: String = ""
        var positionNumber: String = ""



        return binding.root
    }

}