package com.cvelez.challengeyape.data.source.remote.mappers

import com.cvelez.challengeyape.data.source.remote.ApiInfo
import com.cvelez.challengeyape.domain.model.info.Info
import javax.inject.Inject

class InfoMapper @Inject constructor(): Mapper<ApiInfo?, Info> {
    override fun mapToDomain(apiEntity: ApiInfo?): Info {
        return Info(pages = apiEntity?.pages ?: 0)
    }
}