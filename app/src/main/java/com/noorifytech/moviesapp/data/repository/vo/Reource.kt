package com.noorifytech.moviesapp.data.repository.vo

import androidx.lifecycle.LiveData

data class Resource<T>(val data: T?, val status: LiveData<Status>, val msg: String)

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}