package com.h5190067.saatsaatcloneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class SplashActivity : AppCompatActivity() {

    var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        doSplash()
    }

    private fun doSplash() {
        countDownTimer = object: CountDownTimer(Constants.COUNT_DOWN_MILISECOND,Constants.COUNT_DOWN_INTERVAL){
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                changeActivity()
            }
        }.start()
    }
    private fun changeActivity(){
        intent = Intent(this@SplashActivity, LoginPageActivity::class.java)
        startActivity(intent)
        finish()
    }
}