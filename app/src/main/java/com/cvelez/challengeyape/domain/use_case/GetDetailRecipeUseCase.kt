package com.cvelez.challengeyape.domain.use_case

import com.cvelez.challengeyape.data.Result
import com.cvelez.challengeyape.domain.model.Recipe
import com.cvelez.challengeyape.domain.repositories.RecipeRepository
import javax.inject.Inject

class GetDetailRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: Int): Result<Recipe> {
        return repository.getDetailRecipe(id)
    }
}