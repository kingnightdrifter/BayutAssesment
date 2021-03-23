package com.bayut.bayutassignemnt.network

import androidx.lifecycle.MutableLiveData
import com.katch.hi5.data.api.Resource
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response

object MainRepository {
    val isLoading = MutableLiveData<Boolean>()

    fun <T> forRXLiveDataWithLoader(
        target: MutableLiveData<Resource<T>>
    ): Observer<Response<T>> {
        isLoading.postValue(true)
        return object : Observer<Response<T>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(response: Response<T>) {
                isLoading.postValue(false)
                if (response.code() != 200) {
                    target.postValue(
                        Resource.errorBody(
                            response.errorBody()?.string()
                        )
                    )
                } else {
                    target.postValue(Resource.successBody(response.body()))
                }

            }

            override fun onError(t: Throwable) {
                isLoading.postValue(false)
                target.postValue(Resource.errorBody(t.toString()))
            }

            override fun onComplete() {
                isLoading.postValue(false)

            }
        }
    }
}