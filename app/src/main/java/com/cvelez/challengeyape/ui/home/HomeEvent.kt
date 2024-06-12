package com.cvelez.challengeyape.ui.home

sealed class HomeEvent {
    data class EnteredRecipe(val value: String): HomeEvent()
}
