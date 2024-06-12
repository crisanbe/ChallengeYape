package com.cvelez.challengeyape.data.source.remote

import com.cvelez.challengeyape.domain.model.Recipes
import com.google.gson.annotations.SerializedName

data class ApiPaginatedRecipes(
    val info: ApiInfo?,
    @SerializedName("results") val characters: List<Recipes>?
)