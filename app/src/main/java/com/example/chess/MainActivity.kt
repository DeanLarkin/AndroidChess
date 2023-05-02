package com.example.chess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    companion object{
        const val URL = "https://posthere.io/"
        const val  ROUTE = "abc3-44ab-ace8"
        const val USERNAME = "cs3714, Chess"
        const val USER_ID = "Final Project"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}