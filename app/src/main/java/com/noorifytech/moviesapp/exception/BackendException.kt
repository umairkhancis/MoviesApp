package com.noorifytech.moviesapp.exception

data class BackendException(val msg: String): Throwable(msg)