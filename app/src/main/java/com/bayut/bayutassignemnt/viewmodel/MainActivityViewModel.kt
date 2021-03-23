package com.bayut.bayutassignemnt.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bayut.bayutassignemnt.model.Images
import com.bayut.bayutassignemnt.network.MainRepository
import com.katch.hi5.data.api.Resource
import com.katch.hi5.data.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivityViewModel(application: Application) : BaseViewModel(application) {

    private var itemsListMutable: MutableLiveData<Resource<Images>>? = null

    fun getItemsRequest() {
            itemsListMutable = MutableLiveData()

            RetrofitClient.api.getImages()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((MainRepository.forRXLiveDataWithLoader(itemsListMutable!!)))
    }

    fun getList(): MutableLiveData<Resource<Images>>? {
        return itemsListMutable
    }

}

