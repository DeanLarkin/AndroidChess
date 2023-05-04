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
    var player: Int = 1
    var moveList1: MutableList<String?> = mutableListOf<String?>()
    var moveList2: MutableList<String?> = mutableListOf<String?>()
    var min1: Int = 0
    var min2: Int = 0
    var paused: Boolean = false
    var paused1: Boolean = false
    var paused2: Boolean = true
    var count1: Int? = 90
    var count2: Int? = 90
    private val _minLive1 = MutableLiveData<Int>(0)
    private val _minLive2 = MutableLiveData<Int>(0)
    var minLive1: LiveData<Int> = _minLive1
    var minLive2: LiveData<Int> = _minLive2
    private val _countLive1 = MutableLiveData<Int>(0)
    private val _countLive2 = MutableLiveData<Int>(0)
    private val _timerData1 = MutableLiveData<Long>(60000)
    private val _timerData2 = MutableLiveData<Long>(60000)
    var countLive1: LiveData<Int> = _countLive1
    var countLive2: LiveData<Int> = _countLive2
    var timerData1: LiveData<Long> = _timerData1
    var timerData2: LiveData<Long> = _timerData2

    fun setTime(newTime: Int) {
        timeSetting = newTime
    }

    fun getTime(): Int? {
        return timeSetting
    }

    fun setTimerData1(timerData: Long){
        _timerData1.value = timerData
    }

    fun getTimerData1(): Long?{
        return timerData1?.value
    }

    fun setTimerData2(timerData: Long){
        _timerData2.value = timerData
    }

    fun getTimerData2(): Long?{
        return timerData2?.value
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