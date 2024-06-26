package com.cvelez.challengeyape.util

sealed class Results<T> {
    data class Success<T>(val data: T? = null): Results<T>()
    data class Error<T>(val error: ErrorEntity): Results<T>()
    data class Loading<T>(val progress: Int) : Results<T>()
}