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
    private var moveList1: MutableList<String?> = mutableListOf<String?>()
    private var moveList2: MutableList<String?> = mutableListOf<String?>()
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
    var countLive1: LiveData<Int> = _countLive1
    var countLive2: LiveData<Int> = _countLive2

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

    fun timerCounter1() {
        Timer().scheduleAtFixedRate(timerTask {
            Log.e("YYYYYYYY", count1.toString())
            println(paused1)
            if (!paused1) {
                if (count1!! <= 0) {
                    count1 = 59
                    if (min1 > 0) {
                        min1--
                        _minLive1.postValue(min1)
                    }
                    _countLive1.postValue(count1)
                } else {
                    count1 = count1!! - 1
                    _countLive1.postValue(count1)
                }
            }
        }, 0, 1000)
    }

    fun timerCounter2() {
        Timer().scheduleAtFixedRate(timerTask {
            Log.e("YYYYYYYY", count2.toString())
            println(paused2)
            if (!paused2) {
                if (count2!! <= 0) {
                    count2 = 59
                    if (min2 > 0) {
                        min2--
                        _minLive2.postValue(min2)
                    }
                    _countLive2.postValue(count2)
                } else {
                    count2 = count2!! - 1
                    _countLive2.postValue(count2)
                }
            }
        }, 0, 1000)
    }

    fun pause1(){
        paused1 = true
        paused2 = false
    }

    fun pause2(){
        paused1 = false
        paused2 = true
    }

    fun pause() {
        paused1 = true
        paused2 = true
        paused = true
    }

    fun play() {
        paused = false
        if (player == 1) paused1 = true
        else paused2 = true
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