package com.noorifytech.moviesapp.data.repository.vo

data class Resource<T>(val data: T?, val status: Status, val msg: String)

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}