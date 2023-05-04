package com.example.chess

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.chess.databinding.FragmentMoveDisplayBinding

class MoveDisplayFragment : Fragment() {

    private lateinit var binding: FragmentMoveDisplayBinding
    private val cvm: ChessViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoveDisplayBinding.inflate(layoutInflater, container, false)
        binding.movesList.text = formatMoves()

        return binding.root
    }

    private fun formatMoves(): String{

        var finalString = ""

        if (cvm.moveList1.size > 0) {
            for (i in 0..cvm.moveList1.size - 1) {
                if (i >= cvm.moveList2.size)
                    finalString = finalString + (i + 1).toString() + ". " + cvm.moveList1[i]
                else {
                    finalString = finalString + (i + 1).toString() + ". " + cvm.moveList1[i] + " " + cvm.moveList2[i]
                }
            }
        }

        return finalString

    }

}