package com.cvelez.challengeyape.ui.detail

import com.cvelez.challengeyape.domain.model.Recipe

data class DetailState(
    val recipe: Recipe? = null,
    val isLoading: Boolean = false
)