package com.cvelez.challengeyape.di

import com.cvelez.challengeyape.data.repositories.CharacterRepositoryImpl
import com.cvelez.challengeyape.domain.repositories.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindCharacterRepository(impl: CharacterRepositoryImpl): RecipeRepository
}