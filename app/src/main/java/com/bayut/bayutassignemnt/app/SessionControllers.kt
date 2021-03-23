package com.katch.hi5.data.app

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionControllers(private val context: Context) {
    var isUserLogin: Boolean
        get() = sharedPrefs.getBoolean(IS_USER_LOGGED_IN, false)
        set(value) = sharedPrefs.edit { putBoolean(IS_USER_LOGGED_IN, value) }

    var apiToken: String?
        get() = sharedPrefs.getString(API_TOKEN, null)
        set(value) = sharedPrefs.edit { putString(API_TOKEN, value) }

   /* var currentLanguage: LanguagesListItem?
        get() {
            val jsonString = sharedPrefs.getString(
                LanguagesListItemKEY,
                gson.toJson(LanguagesListItem(1, 0, "en", "English"))
            ) ?: return null
            return gson.fromJson(jsonString, object : TypeToken<LanguagesListItem>() {}.type)
        }
        set(value) = sharedPrefs.edit {
            putString(LanguagesListItemKEY, gson.toJson(value))
        }
    var currentUser: User?
        get() {
            val jsonString = sharedPrefs.getString(
                currentUserKey, null
            ) ?: return null
            return gson.fromJson(jsonString, object : TypeToken<User>() {}.type)
        }
        set(value) = sharedPrefs.edit {
            putString(currentUserKey, gson.toJson(value))
        }


    // for logout user and clear the cache
    fun logoutUser(activity: Activity) {
        ConstantValues.prefs.isUserLogin = false
        ConstantValues.prefs.currentUser = null
        ConstantValues.API_TOKEN = ""
        activity.startActivity(
            ConstantValues.goIntent(
                activity,
                OnBoardingActivity::class.java
            )
        )
        activity.finishAffinity()
    }
*/
    companion object {
        val gson = Gson()
        private val PREF_NAME = "Katch_Prefs"
        private val LanguagesListItemKEY = "LanguagesListItem"
        private val currentUserKey = "currentUser"
        private val API_TOKEN = "API_TOKEN"
        private val Device_id = "Device_id"
        private val IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN"
    }

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

}