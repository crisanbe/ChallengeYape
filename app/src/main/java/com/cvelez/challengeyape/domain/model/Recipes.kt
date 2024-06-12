package com.cvelez.challengeyape.domain.model

import com.cvelez.challengeyape.data.source.remote.dto.Location

data class Recipes(
    val id: Int,
    val name: String,
    val image: String,
    val location: Location,
)
