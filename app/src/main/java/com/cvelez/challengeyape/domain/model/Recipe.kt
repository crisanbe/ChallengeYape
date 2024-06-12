package com.cvelez.challengeyape.domain.model

import com.cvelez.challengeyape.data.source.remote.dto.Location

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val location: Location,
    val image: String
)
