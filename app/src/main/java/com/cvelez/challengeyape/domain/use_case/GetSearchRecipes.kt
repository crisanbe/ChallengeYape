package com.cvelez.challengeyape.domain.use_case

import com.cvelez.challengeyape.domain.repositories.RecipeRepository
import com.cvelez.challengeyape.util.ErrorEntity
import com.cvelez.challengeyape.util.MIN_NAME_LENGTH
import com.cvelez.challengeyape.util.Results
import javax.inject.Inject

class GetSearchRecipes @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(page: Int, input: String) =
        if (input.length < MIN_NAME_LENGTH ) {
            Results.Error(ErrorEntity.InputError.NameError)
        } else {
            repository.getSearchRecipes(page, input)
        }
}