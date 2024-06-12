package com.cvelez.challengeyape.data.source.remote

import com.cvelez.challengeyape.data.source.remote.dto.RecipeDto
import com.cvelez.challengeyape.data.source.remote.dto.CharactersDto
import com.cvelez.challengeyape.util.ID_PARAMETER
import com.cvelez.challengeyape.util.NAME_PARAMETER
import com.cvelez.challengeyape.util.PAGE_PARAMETER
import com.cvelez.challengeyape.util.RECIPE_DETAIL_ENDPOINT
import com.cvelez.challengeyape.util.RECIPE_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET(RECIPE_ENDPOINT)
    suspend fun getRecipes(
        @Query(PAGE_PARAMETER) page: Int,
    ): CharactersDto

    @GET(RECIPE_DETAIL_ENDPOINT)
    suspend fun getDetailRecipe(
        @Path(ID_PARAMETER) id: Int
    ): RecipeDto

    @GET(RECIPE_ENDPOINT)
    suspend fun getSearchRecipes(
        @Query(PAGE_PARAMETER) id: Int,
        @Query(NAME_PARAMETER) name: String
    ): ApiPaginatedRecipes
}