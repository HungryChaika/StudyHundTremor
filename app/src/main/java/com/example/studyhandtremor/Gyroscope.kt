package com.example.studyhandtremor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class Gyroscope(context: Context, val dataCollection: DataCollection) : SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_GYROSCOPE && dataCollection.getFlagDataCollectionAllowed()) {
            dataCollection.addElemToData(event.values)
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    fun registerGyroscopeListener() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun unRegisterGyroscopeListener() {
        sensorManager.unregisterListener(this)
    }

}