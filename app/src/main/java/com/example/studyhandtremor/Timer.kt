package com.example.studyhandtremor

import android.os.CountDownTimer

class Timer(
    millisTotal: Long = 10000,
    millisInterval: Long = 1000,
    private val funsOnFinish: Array<() -> Unit>?
) : CountDownTimer(millisTotal, millisInterval) {

    override fun onTick(millisUntilFinished: Long) {}
    override fun onFinish() {
        funsOnFinish?.forEach {
            it.invoke()
        }
    }

}
