package com.cvelez.challengeyape.data.source.remote.mappers

import com.cvelez.challengeyape.domain.model.Recipe
import com.cvelez.challengeyape.domain.model.Recipes
import javax.inject.Inject

class RecipesMapper @Inject constructor(
    private val apiLocationMapper: LocationMapper
): Mapper<Recipes?, Recipe> {
    override fun mapToDomain(apiEntity: Recipes?): Recipe {
        return Recipe(
            id = apiEntity?.id ?: throw MappingException("Result ID cannot be null"),
            name = apiEntity.name.orEmpty(),
            description = apiEntity.toString(),
            image = apiEntity.image.orEmpty(),
            location = apiLocationMapper.mapToDomain(apiEntity.location)
        )
    }
}