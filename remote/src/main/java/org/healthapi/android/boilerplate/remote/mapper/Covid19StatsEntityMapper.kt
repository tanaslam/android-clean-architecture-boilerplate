package org.healthapi.android.boilerplate.remote.mapper

import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.remote.model.Covid19StatsModel
import javax.inject.Inject

/**
 * Map a [Covid19StatsModel] to and from a [Covid19StatsEntity] instance when data is moving between
 * this later and the Data layer
 */
open class Covid19StatsEntityMapper @Inject constructor() : EntityMapper<Covid19StatsModel, Covid19StatsEntity> {

    /**
     * Map an instance of a [Covid19StatsModel] to a [Covid19StatsEntity] model
     */
    override fun mapFromRemote(model: Covid19StatsModel): Covid19StatsEntity {
        return Covid19StatsEntity("N/A", model.country, model.country_code ?: "-",
                model.last_update, model.confirmed, model.deaths, model.recovered)
    }

}