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
        var moveString: String = ""

        binding.submitButton.setOnClickListener{

            pieceName = binding.piece.selectedItemId.toString()
            positionLetter = binding.letter.selectedItemId.toString()
            positionNumber = binding.number.selectedItemId.toString()
            if (binding.taken.isChecked){
                moveString = pieceName + "x" + positionLetter + positionNumber
            } else {
                moveString = pieceName + positionLetter + positionNumber
            }

            cvm.addMove(moveString)

        }

        return binding.root
    }

}