package com.bayut.bayutassignemnt.view

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bayut.bayutassignemnt.app.ConstantValues
import com.bayut.bayutassignemnt.utils.LocaleHelper
import com.bayut.bayutassignemnt.view.dialogs.LoadingDialog
import com.bayut.bayutassignemnt.viewmodel.BaseViewModel


abstract class BaseActivity : AppCompatActivity() {

   lateinit var dialogLoader: LoadingDialog

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (ConstantValues.IS_RTL == 1) {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        } else {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogLoader = LoadingDialog(this, true)

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.wrapLocale(newBase, ConstantValues.languageCode))
    }

    // hide the softkeyboard if open
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }


    open fun showProgress() {
        dialogLoader.showProgressDialog()
    }

    open fun showProgress(cancelable: Boolean) {
        dialogLoader.showProgressDialog(cancelable)
    }

    open fun hideProgress() {
        dialogLoader.hideProgressDialog()
    }

    open fun showOrHideProgressBar(baseViewModel: BaseViewModel) {
        baseViewModel.getIsLoading().observe(this,
            Observer { aBoolean ->
                if (aBoolean) {
                    showProgress()
                } else {
                    hideProgress()
                }
            })
    }



}