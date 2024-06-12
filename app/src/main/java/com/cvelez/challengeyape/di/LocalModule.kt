package com.cvelez.challengeyape.di

import android.app.Application
import androidx.room.Room
import com.cvelez.challengeyape.data.source.remote.local.Cache
import com.cvelez.challengeyape.data.source.remote.local.RecipeDatabase
import com.cvelez.challengeyape.data.source.remote.local.RoomCache
import com.cvelez.challengeyape.data.source.remote.local.daos.RecipesDao
import com.cvelez.challengeyape.util.DB_NAME
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(app: Application): RecipeDatabase {
            return Room.databaseBuilder(
                app,
                RecipeDatabase::class.java,
                DB_NAME
            ).build()
        }

        @Provides
        fun provideRecipeDao(db: RecipeDatabase): RecipesDao = db.recipeDao()
    }
}