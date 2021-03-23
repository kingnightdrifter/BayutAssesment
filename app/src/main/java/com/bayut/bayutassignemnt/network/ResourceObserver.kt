package com.katch.hi5.data.api

import androidx.lifecycle.Observer

object ResourceObserver {
    fun <T> decorateWithErrorHandling(
        decorated: Observer<T>,
        errorView: ErrorView
    ): Observer<Resource<T>> {
        return Observer { resource: Resource<T> ->
            val t: String? = resource.errorData
            if (t != null) {
                errorView.showError(t)
            } else {
                decorated.onChanged(resource.successData)
            }
        }
    }
}