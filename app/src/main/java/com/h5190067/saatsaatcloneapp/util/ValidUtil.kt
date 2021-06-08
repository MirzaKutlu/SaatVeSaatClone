package com.h5190067.saatsaatcloneapp.util

import android.util.Patterns

object ValidUtil {

    fun isValidEmail(mailText: CharSequence): Boolean {
        if (mailText.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(mailText).matches()) {
            return true
        }
        return false
    }

    fun isNotEmptyText(targetText: CharSequence): Boolean {
        return targetText.isNotEmpty()
    }

}