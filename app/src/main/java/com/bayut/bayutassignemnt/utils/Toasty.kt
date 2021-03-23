package com.katch.hi5.utils

import android.content.Context
import android.widget.Toast


class Toasty(var context: Context) {
    fun showErrorToasty(msg: String) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show()
    }

    fun showSuccessToasty(msg: String) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show()
    }

    fun showInfoToasty(msg: String) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show()
    }

    fun ShowWarningToasty(msg: String) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show()
    }

    fun showConfusingToast(msg: String) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show()
    }

    fun showDefaultToast(msg: String) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show()
    }
}