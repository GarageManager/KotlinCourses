package com.example.task2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val text = findViewById<TextView>(R.id.secondActivityTextContainer)
        val num = intent.getStringExtra("counter").toString().toInt()
        text.text = (num * num).toString()
        Log.i(this.localClassName, "created!")
    }
}