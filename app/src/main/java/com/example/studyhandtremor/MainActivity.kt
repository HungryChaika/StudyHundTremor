package com.example.studyhandtremor

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.GoToAnalize).setOnClickListener { onClickGoToAnalize() }
    }

    @SuppressLint("SimpleDateFormat")
    private fun onClickGoToAnalize() {
        val CurrentDateAndTime = SimpleDateFormat("dd-MM-yyyy_hh:mm:ss")
            .format(Calendar.getInstance().time)
        val editText: TextView = findViewById(R.id.EditText)
        if("[A-Za-zА-Яа-я0-9]+".toRegex().matches(editText.text.toString())) {
            val intent = Intent(this, AnalizeActivity::class.java)
            intent.putExtra("UserName", editText.text.toString())
            intent.putExtra("DateTime", CurrentDateAndTime)
            startActivity(intent)
        } else {
            editText.text = ""
        }
    }

}