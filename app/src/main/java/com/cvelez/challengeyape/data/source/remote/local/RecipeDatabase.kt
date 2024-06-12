package com.cvelez.challengeyape.data.source.remote.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cvelez.challengeyape.data.source.remote.local.daos.RecipesDao
import com.cvelez.challengeyape.data.source.remote.local.model.characters.CachedRecipes

@Database(entities = [CachedRecipes::class], version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipesDao
}