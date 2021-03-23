package com.katch.hi5.data.api

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

class NetworkState(val status: Status, val msg: String) {

    companion object {
        val SUCCESS: NetworkState = NetworkState(Status.SUCCESS, "SUCCESS")
        val LOADING: NetworkState = NetworkState(Status.LOADING, "LOADING")
        val ERROR: NetworkState = NetworkState(Status.ERROR, "ERROR")
    }
}