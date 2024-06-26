package com.cvelez.challengeyape.data.source.remote.local

import com.cvelez.challengeyape.data.source.remote.local.model.characters.CachedRecipes
import kotlinx.coroutines.flow.Flow

interface Cache {

    fun getRecipes(input: String, limit: Int, offset: Int): Flow<List<CachedRecipes>>

    suspend fun storeRecipes(recipes: List<CachedRecipes>)
}