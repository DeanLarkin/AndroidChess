package com.example.chess

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.*
import java.util.Timer
import kotlin.concurrent.timerTask

class ChessViewModel : ViewModel() {

    private var timeSetting: Int? = null
    private var theme: String? = null
    private var turn: Int = 0 //0 if player 1, 1 if player 2
    private var moveCount1: Int = 0
    private var moveCount2: Int = 0
    private var player: Int = 1
    private var moveList1: MutableList<String?> = mutableListOf<String?>()
    private var moveList2: MutableList<String?> = mutableListOf<String?>()
    var min: Int = 0
    var paused: Boolean = false
    var count: Int? = 90
    private val _minLive = MutableLiveData<Int>(0)
    var minLive: LiveData<Int> = _minLive
    private val _countLive = MutableLiveData<Int>(0)
    var countLive: LiveData<Int> = _countLive
//g
//    init{
//        timerCounter()
//    }
    fun setTime(newTime: Int) {
        timeSetting = newTime
    }

    fun getTime(): Int? {
        return timeSetting
    }

    fun post(text: String){
        val inputData = Data.Builder().putString("username", MainActivity.USERNAME)
            .putString("date", Calendar.getInstance().time.toString())
            .putString("userID", MainActivity.USER_ID)
            .putString("event", text).build()

        val uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
        val setUploadWorkRequestWithInputData = uploadWorkRequest.setInputData(inputData).build()
        WorkManager.getInstance().beginUniqueWork(text, ExistingWorkPolicy.REPLACE, setUploadWorkRequestWithInputData)
            .enqueue()
    }
    fun timerCounter() {
        Timer().scheduleAtFixedRate(timerTask {
            Log.e("YYYYYYYY", count.toString())
            println(paused)
            if (!paused) {
                if (count!! <= 0) {  // check if count is less than or equal to 0
                    Log.e("Count Check <= 0", count.toString())
                    count = 59  // reset count to 59
                    if (min > 0) {  // decrement min only if it is greater than 0  make sure it is running.
                        min--
                        _minLive.postValue(min)
                    }
                    _countLive.postValue(count)
                } else {
                    count = count!! - 1  // decrement count by 1
                    _countLive.postValue(count)
                    Log.e("Count > 0", count.toString())
                }
            }
        }, 0, 1000)
    }

    fun pause(){
        paused = true;
    }

    fun notpause(){
        paused = false;
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