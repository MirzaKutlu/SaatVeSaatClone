package com.h5190067.saatsaatcloneapp.util

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import com.h5190067.saatsaatcloneapp.R

object AlertUtil {

    fun giveAlert(activity: Activity, title: String, message: String, isNoConnectionAlert: Boolean ){
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(message)
        if (isNoConnectionAlert) {
            builder.setNegativeButton(
                R.string.ExitApp,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    activity.finish()
                })
            builder.setPositiveButton(R.string.GoSettings,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    activity.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                    activity.finish()
                })
        } else {
            builder.setNegativeButton(R.string.ExitNo,
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            builder.setPositiveButton(R.string.ExitYes,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    activity.finish()
                })
        }
        builder.show()
    }
}
