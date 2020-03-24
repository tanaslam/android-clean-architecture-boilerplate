package org.healthapi.android.boilerplate.data.mapper

import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import javax.inject.Inject


/**
 * Map a [Covid19StatsEntity] to and from a [Covid19Stats] instance when data is moving between
 * this later and the Domain layer
 */
open class Covid19StatsDataMapper @Inject constructor() : Mapper<Covid19StatsEntity, Covid19Stats> {

    /**
     * Map a [Covid19StatsEntity] instance to a [Covid19Stats] instance
     */
    override fun mapFromEntity(entity: Covid19StatsEntity): Covid19Stats {
        return Covid19Stats(entity.state, entity.country, entity.countryCode,
                entity.lastUpdate, entity.confirmed, entity.deaths, entity.recovered)
    }

    /**
     * Map a [Covid19Stats] instance to a [Covid19StatsEntity] instance
     */
    override fun mapToEntity(model: Covid19Stats): Covid19StatsEntity {
        return Covid19StatsEntity(model.state, model.country, model.countryCode,
                model.lastUpdate, model.confirmed, model.deaths, model.recovered)
    }


}