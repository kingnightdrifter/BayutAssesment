package com.katch.hi5.data.api

interface ErrorView {
    fun showError(message: String?)
    fun TimberLog(tag: String): Any
}