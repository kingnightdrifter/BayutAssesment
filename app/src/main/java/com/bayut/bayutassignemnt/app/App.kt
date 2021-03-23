package com.katch.hi5.data.app

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

import com.bayut.bayutassignemnt.app.ConstantValues

import com.google.gson.Gson

import com.katch.hi5.utils.LogPrint


class App : MultiDexApplication() {

    var sessionControllers: SessionControllers? = null

    companion object {
        private const val TAG = "App"
        val timberLog = LogPrint(TAG)
        val gson = Gson()
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)
        //for google autocomplete

        sessionControllers = SessionControllers(instance)
        ConstantValues.API_TOKEN = sessionControllers!!.apiToken.toString()
        timberLog.e("TOKEN: " + ConstantValues.API_TOKEN)
    }
}