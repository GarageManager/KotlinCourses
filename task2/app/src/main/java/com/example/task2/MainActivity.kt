package com.example.task2

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var counter = 0
    private var secondActivityRecentlyOpened = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTextToMainLayout(counter.toString())

        val sendButton = findViewById<Button>(R.id.send_button_id)
        val sendText = findViewById<TextView>(R.id.firstActivityTextContainer)

        sendButton.setOnClickListener {
            secondActivityRecentlyOpened = true
            val intent = Intent(applicationContext, SecondActivity::class.java)
            intent.putExtra("counter", sendText.text.toString())
            startActivity(intent)
        }
        Log.i(this.localClassName, "created!")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        if (!secondActivityRecentlyOpened) {
            counter++
            setTextToMainLayout(counter.toString())
        } else {
            secondActivityRecentlyOpened = false
        }
        super.onConfigurationChanged(newConfig)
        Log.i(this.localClassName, "configuration changed!")
    }

    private fun setTextToMainLayout(text: String) {
        val textView: TextView = findViewById(R.id.firstActivityTextContainer)
        textView.text = text
    }
}