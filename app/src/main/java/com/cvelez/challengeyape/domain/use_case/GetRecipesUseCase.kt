package com.cvelez.challengeyape.domain.use_case

import com.cvelez.challengeyape.domain.model.Recipes
import com.cvelez.challengeyape.domain.repositories.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(name: String, limit: Int, offset: Int): Flow<List<Recipes>> {
        return repository.getRecipes(name, limit, offset).filter { it.isNotEmpty() }
    }
}