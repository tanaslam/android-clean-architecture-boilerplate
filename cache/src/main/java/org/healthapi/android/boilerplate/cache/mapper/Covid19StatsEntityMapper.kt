package org.healthapi.android.boilerplate.cache.mapper

import org.healthapi.android.boilerplate.cache.model.CachedCovid19Stats
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import javax.inject.Inject

/**
 * Map a [CachedCovid19Stats] instance to and from a [Covid19StatsEntity] instance when data is moving between
 * this later and the Data layer
 */
class Covid19StatsEntityMapper @Inject constructor() : EntityMapper<CachedCovid19Stats, Covid19StatsEntity> {

    /**
     * Map a [Covid19StatsEntity] instance to a [CachedCovid19Stats] instance
     */
    override fun mapToCached(entity: Covid19StatsEntity): CachedCovid19Stats {
        return CachedCovid19Stats(entity.state, entity.country, entity.countryCode,
                entity.lastUpdate, entity.confirmed, entity.deaths, entity.recovered)
    }

    /**
     * Map a [CachedCovid19Stats] instance to a [Covid19StatsEntity] instance
     */
    override fun mapFromCached(cache: CachedCovid19Stats): Covid19StatsEntity {
        return Covid19StatsEntity(cache.state, cache.country, cache.countryCode,
                cache.lastUpdate, cache.confirmed, cache.deaths, cache.recovered)
    }

}