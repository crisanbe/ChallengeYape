package com.cvelez.challengeyape.domain.use_case

import com.cvelez.challengeyape.domain.model.Recipes
import com.cvelez.challengeyape.domain.repositories.RecipeRepository
import com.cvelez.challengeyape.util.Results
import javax.inject.Inject

class GetListRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(page: Int): Results<List<Recipes>> {
        return repository.getListRecipe(page = page)
    }
}