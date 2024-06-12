package com.cvelez.challengeyape.data.source.remote.mappers

interface Mapper<E, D> {
    fun mapToDomain(apiEntity: E): D
}