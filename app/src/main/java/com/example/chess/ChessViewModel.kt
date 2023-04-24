package com.example.chess

import androidx.lifecycle.ViewModel

class ChessViewModel : ViewModel() {

    private var timeSetting: Int? = null
    private var theme: String? = null
    private var moveCount1: Int = 0
    private var moveCount2: Int = 0
    private var player: Int = 1
    private var moveList1: MutableList<String?> = mutableListOf<String?>()
    private var moveList2: MutableList<String?> = mutableListOf<String?>()

    fun setTime(newTime: Int) {
        timeSetting = newTime
    }

    fun getTime(): Int? {
        return timeSetting
    }

    fun setTheme(newTheme: String) {
        theme = newTheme
    }

    fun getTheme(): String? {
        return theme
    }

    fun addMove(move: String) {
        if (player == 1){
            moveList1.add(move)
        } else {
            moveList2.add(move)
        }
    }

    fun setMoveCount() {
        if (player == 1){
            moveCount1++
        } else {
            moveCount2++
        }
    }

    fun getMoveCount() : Int{
        if (player == 1){
            return moveCount1
        } else {
            return moveCount2
        }
    }

}