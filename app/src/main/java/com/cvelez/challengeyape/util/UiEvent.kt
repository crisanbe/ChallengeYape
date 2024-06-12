package com.cvelez.challengeyape.util

sealed class UiEvents {
    object Success : UiEvents()
    object Error : UiEvents()
    object NavigateUp : UiEvents()
    data class ShowSnackBar(val message: UiText) : UiEvents()
    data class ErrorOkHttpClient(val message: UiText) : UiEvents()
}
