package com.haeyum.loginassignment

import android.widget.Toast
import com.haeyum.loginassignment.MainApp.Companion.context

class ToastHelper {
    companion object {
        fun showToast(msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}