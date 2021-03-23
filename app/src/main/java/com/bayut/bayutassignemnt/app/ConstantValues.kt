package com.bayut.bayutassignemnt.app

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bayut.bayutassignemnt.utils.GlideApp
import com.katch.hi5.data.app.App
import com.katch.hi5.data.app.SessionControllers
import com.bayut.bayutassignemnt.view.dialogs.LoadingDialog
import java.util.*

class ConstantValues {
    companion object {
        const val NameRegex = "[ .a-zA-Z]+";
        const val PhoneNumRegex = "^[0-9-]+$"
        const val PasswordRegex =
            "[A-Z|a-z|0-9]{6,15}\$" //"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        private const val TAG = "ConstantValues"

        var URL: String = "https://app.katch.hi5host.com/"
        var BASE_URL: String = "https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/default/"
        var languageCode: String = "en"
        var IS_RTL = 0
        var API_TOKEN: String = ""
        var DEVICE_OS: String = "Android"
        var FCM_TOKEN: String =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMGZhYmNjZDhiODNjYmU4NjFiNGM4MDJjMGMxYTU2MmRiNDE0YTZhNzIxOGE1M2M2YTk3NTlmYWI3ZWVjODllYzkzNGFlZjc4ODA3MDMzNTIiLCJpYXQiOjE2MDI5MjkyNTAsIm5iZiI6MTYwMjkyOTI1MCwiZXhwIjoxNjM0NDY1MjUwLCJzdWIiOiIyMSIsInNjb3BlcyI6W119.EQ5SAc11OdSQxHIDB5112xBfMS8WB7LjRPVWTuLKnTW4smlUy8LWgDtCcVn80gwCjVsAjOlpqgu0Jrl9D_qAUDGO4eA850vD6TKlBLK1Xxk1ZLad-Cpk5gpbH8iQ_JJq3F13lxrPoTguxJ76ST7kC1pJWouSZfiDwF0i7rM93LAwVf3XVBRh5ojM-cK7cB4OilnU3fSvo3FZNYeQZFUipez--W80wi-8wr7L8LFaeVCS9ERcy04r52NVVR1Wn-kufYiJwPFOtmq-pMaXd9SOHuGvvqTF_Ia1vJlCPicfqR56Em3aq9yuJEkrfuTR8Dov3L9nWGMafPXXbwLYhY4uAdMPviaf3AHXwTr8G84bPlggxckrO0C6Nb57cq9-z0luXzS5HjoeDsdKO-if-E4UP0pBMSOmIR2Cgz_saA2XowKI1MmR4hTJYiYzLIMSXg5Tp_EG3PGClJXMoAxOGK8dIw43sn-H2EhASLdYP-prtJ1zikB1Ra04zsBYcYp0AaM3HAnGEFhPXeI9LArjTOH_Q_4z9KWzWdeAw1C70Kew4zYgbcPP5Takv5_3sdFYC0UYVKDP9vYuqkGeo8lYsokmi77kceQCd7O5Aa_L5XjN83GcWdhM4TzZwye95i0_RPSFEYwRxwx1L7hV43UzA3EVJpc2L0h2UrPLhxQqcrHCReM"
        var IS_USER_LOGGED_IN: Boolean = false
        var languageString = "languageString"

        fun <T> goIntent(from: Context, to: Class<T>) = Intent(from, to).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        fun <T> goIntentWithBundle(tag: String, bundle: Bundle, from: Context, to: Class<T>) =
            Intent(from, to).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                putExtra(tag, bundle)
            }

        fun <T> goIntentWithHashMap(
            tag: String,
            bundle: HashMap<String, String>,
            from: Context,
            to: Class<T>
        ) =
            Intent(from, to).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                putExtra(tag, bundle)
            }
/*

        fun getFCMToken() {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    timberLog.e("FCM_Token:Fetching FCM registration token failed " + task.exception)
                    return@OnCompleteListener
                }
                // Get new FCM registration token
                val token = task.result
                if (token != null) {
                    FCM_TOKEN = token
                    timberLog.e("FCM_Token:$token")
                } else {
                    timberLog.e("FCM_Token:$token")
                }
            })
        }
*/


        @SuppressLint("HardwareIds")
        fun deviceId(activity: Activity): String {
            return "" + Settings.Secure.getString(
                activity.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        }

        fun <T> goIntentWithoutClear(from: Context, to: Class<T>) = Intent(from, to).apply {

        }

        val prefs: SessionControllers by lazy {
            SessionControllers(App.instance)
        }

        val glideApp: GlideApp by lazy {
            GlideApp(App.instance)
        }

        fun preventDoubleClick(view: View) {
            view.isEnabled = false
            enabledView(view)
        }

        fun hideKeyBoard(activity: Activity, view: View) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        private fun enabledView(view: View) {
            Handler(Looper.getMainLooper()).postDelayed({ view.isEnabled = true }, 500)
        }
    }
}