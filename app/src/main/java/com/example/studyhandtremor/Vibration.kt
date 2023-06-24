package com.example.studyhandtremor

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class Vibration(context: Context, val ms: Long = 200) {
    private var vibratorManager: VibratorManager
    private var vibrator: Vibrator

    init {
        vibratorManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        } else {
            TODO("VERSION.SDK_INT < S")
        }
        vibrator = vibratorManager.defaultVibrator
    }

    fun activateVibration() {
        val effect: VibrationEffect =
            VibrationEffect.createOneShot(ms, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.cancel()
        vibrator.vibrate(effect)
    }

}