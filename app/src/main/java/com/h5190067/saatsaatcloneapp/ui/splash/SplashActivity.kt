package com.h5190067.saatsaatcloneapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.h5190067.saatsaatcloneapp.R
import com.h5190067.saatsaatcloneapp.ui.login.LoginPageActivity
import com.h5190067.saatsaatcloneapp.util.AlertUtil
import com.h5190067.saatsaatcloneapp.util.Alerts
import com.h5190067.saatsaatcloneapp.util.Constants
import com.h5190067.saatsaatcloneapp.util.NetworkUtil

class SplashActivity : AppCompatActivity() {

    var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        doSplash()
    }

    private fun doSplash() {
        countDownTimer = object: CountDownTimer(Constants.COUNT_DOWN_MILISECOND, Constants.COUNT_DOWN_INTERVAL){
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                changeActivity()

            }
        }.start()
    }
    private fun changeActivity() {
        if (NetworkUtil.checkNetworkConnection(applicationContext)) {
            startActivity(Intent(this@SplashActivity, LoginPageActivity::class.java))
            finish()
        }else{
                AlertUtil.giveAlert(
                    this,
                    getString(R.string.AlertTitle),
                    getString(R.string.AlertMessage),
                    Alerts.NO_CONN_ALERT
                )
        }
    }
}