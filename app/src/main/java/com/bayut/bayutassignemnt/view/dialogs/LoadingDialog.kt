package com.bayut.bayutassignemnt.view.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater

import android.view.Window
import androidx.appcompat.app.AlertDialog
import com.bayut.bayutassignemnt.R

import java.util.*

class LoadingDialog(private val activity: Context, isCancelable: Boolean) {
    private var alertDialog: AlertDialog? = null
    private val layoutInflater: LayoutInflater = LayoutInflater.from(activity)
    private fun initDialog(isCancelable: Boolean) {
        val dialog = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.loading_dialog_layout, null)
        dialog.setView(dialogView)
        dialog.setCancelable(isCancelable)
        alertDialog = dialog.create()
        alertDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        Objects.requireNonNull(alertDialog!!.window)
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun showProgressDialog() {
        if (alertDialog != null) {
            alertDialog!!.show()
            alertDialog!!.setCancelable(false)
        }
    }

    fun showProgressDialog(cancelable: Boolean) {
        if (alertDialog != null) {
            alertDialog!!.show()
            alertDialog!!.setCancelable(cancelable)
        }
    }

    fun hideProgressDialog() {
        if (alertDialog != null) {
            alertDialog!!.hide()
            alertDialog!!.dismiss()
        }
    }

    init {
        initDialog(isCancelable)
    }
}