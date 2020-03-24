package org.healthapi.android.boilerplate.presentation.mapper

import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import org.healthapi.android.boilerplate.presentation.model.Covid19StatsView
import javax.inject.Inject

/**
 * Map a [Covid19StatsView] to and from a [Covid19Stats] instance when data is moving between
 * this layer and the Domain layer
 */
open class Covid19StatsViewMapper @Inject constructor() : Mapper<Covid19StatsView, Covid19Stats> {

    /**
     * Map a [Covid19Stats] instance to a [Covid19StatsView] instance
     */
    override fun mapToView(model: Covid19Stats): Covid19StatsView {
        return Covid19StatsView(model.state, model.country, model.countryCode,
                model.lastUpdate, model.confirmed, model.deaths, model.recovered)
    }


}