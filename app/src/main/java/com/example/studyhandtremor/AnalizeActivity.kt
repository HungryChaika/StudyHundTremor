package com.example.studyhandtremor

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AnalizeActivity : AppCompatActivity() {
    private lateinit var vibration: Vibration
    private lateinit var timer: Timer
    private lateinit var gyroscope: Gyroscope
    private lateinit var dataCollection: DataCollection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analize)
        dataCollection = DataCollection()
        vibration = Vibration(this)
        gyroscope = Gyroscope(this, dataCollection)
        timer = Timer(5000, 1000, arrayOf(
            { vibration.activateVibration() },
            { dataCollection.prohibitDataCollection() },
            { sendDataToFile() }
        ))
        setEventOnButtonAnalize()
    }

    override fun onResume() {
        super.onResume()
        gyroscope.registerGyroscopeListener()
    }

    override fun onPause() {
        super.onPause()
        gyroscope.unRegisterGyroscopeListener()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setEventOnButtonAnalize() {
        findViewById<Button>(R.id.vibrationButton).setOnTouchListener(
            fun(_: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        vibration.activateVibration()
                        timer.start()
                        dataCollection.clearData()
                        dataCollection.allowDataCollection()
                    }

                    MotionEvent.ACTION_UP -> {
                        timer.cancel().also {
                            dataCollection.prohibitDataCollection()
                        }
                    }
                }
                return true
            }
        )
    }

    fun sendDataToFile() {
        val dataToFile: DataToFile = DataToFile()
        val dataJson: String = dataCollection.convertDataToJson()
        val fileName: String = intent.getStringExtra("UserName") + "_" + intent.getStringExtra("DateTime")
        dataToFile.send(this, fileName, dataJson)
    }

}