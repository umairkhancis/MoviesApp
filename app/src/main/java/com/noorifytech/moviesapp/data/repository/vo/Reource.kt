package com.noorifytech.moviesapp.data.repository.vo

import androidx.lifecycle.LiveData

data class Resource<T>(
    val data: LiveData<T>?,
    val networkStatus: LiveData<NetworkStatus>,
    val msg: String
)

enum class NetworkStatus {
    LOADING,
    SUCCESS,
    FAILED
}