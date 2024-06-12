package com.cvelez.challengeyape.data.source.remote.local.model.characters

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cvelez.challengeyape.data.source.remote.dto.Location
import com.cvelez.challengeyape.domain.model.Recipe
import com.cvelez.challengeyape.domain.model.Recipes

@Entity(tableName = "recipes")
data class CachedRecipes(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    @ColumnInfo(name = "latitude") val latitudeName: String,
    @ColumnInfo(name = "longitude") val longitudeName: String,
    val description: String,
    val image: String,
) {
    companion object {
        fun fromDomain(domainModel: Recipe): CachedRecipes {
            val location = domainModel.location
            return CachedRecipes(
                id = domainModel.id,
                name = domainModel.name,
                description = domainModel.description,
                latitudeName = location.latitude,
                longitudeName = location.longitude,
                image = domainModel.image,
            )
        }
    }

    fun toDomain(): Recipes {
        return Recipes(
            id = id,
            name = name,
            location = Location(latitude = latitudeName, longitude = longitudeName),
            image = image,
        )
    }
}