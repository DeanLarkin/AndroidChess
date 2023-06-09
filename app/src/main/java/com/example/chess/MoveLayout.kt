package com.example.chess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.chess.databinding.FragmentMoveLayoutBinding
//g
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

            pieceName = binding.piece.selectedItem.toString()
            positionLetter = binding.letter.selectedItem.toString()
            positionNumber = binding.number.selectedItem.toString()
            if (binding.taken.isChecked){
                moveString = pieceName + " "+ "x" +  positionLetter + positionNumber
            } else {
                moveString = pieceName + positionLetter + positionNumber
            }
            cvm.post(moveString)

            cvm.addMove(moveString)

        }

        return binding.root
    }

}