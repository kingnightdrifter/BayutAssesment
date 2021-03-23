package com.bayut.bayutassignemnt.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.bayut.bayutassignemnt.network.MainRepository

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    fun setLoading(loading: Boolean) {
        MainRepository.isLoading.postValue(loading)
    }

    fun getIsLoading(): LiveData<Boolean> {
        return MainRepository.isLoading
    }
}