package com.cvelez.challengeyape.domain.repositories

import com.cvelez.challengeyape.data.Result
import com.cvelez.challengeyape.domain.model.Recipe
import com.cvelez.challengeyape.domain.model.Recipes
import com.cvelez.challengeyape.util.Results
import com.cvelez.challengeyape.domain.model.info.Info
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getListRecipe(page: Int): Results<List<Recipes>>

    fun getRecipes(input: String, limit: Int, offset: Int): Flow<List<Recipes>>

    suspend fun getDetailRecipe(id: Int): Result<Recipe>

    suspend fun getSearchRecipes(pageToLoad: Int, name: String): Results<Info>
}