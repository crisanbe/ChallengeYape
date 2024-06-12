package com.cvelez.challengeyape.data.source.remote.dto

import com.cvelez.challengeyape.domain.model.Recipe

data class RecipeDto(
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val description: String,
)

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        id = id,
        name = name,
        location = location,
        image = image,
        description = description,
    )
}