package com.h5190067.saatsaatcloneapp.util

import android.app.ProgressDialog
import android.content.Context
import com.h5190067.saatsaatcloneapp.R

object ProggresDialogUtil {

    var prgrsDialog: ProgressDialog? = null

    fun showDialog(context: Context, loadingTitle: String){
        prgrsDialog = ProgressDialog(context)

        prgrsDialog!!.apply {
            setTitle(loadingTitle)
            setMessage(context.getString(R.string.progDialog))
            setCancelable(false)
            show()
        }
    }
    fun dontShowDialog(){
        prgrsDialog!!.let {
            it.dismiss()
        }
    }
}