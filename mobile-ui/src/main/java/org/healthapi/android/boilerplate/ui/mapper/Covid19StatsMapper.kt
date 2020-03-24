package org.healthapi.android.boilerplate.ui.mapper

import org.healthapi.android.boilerplate.presentation.model.Covid19StatsView
import org.healthapi.android.boilerplate.ui.model.Covid19StatsViewModel
import javax.inject.Inject

/**
 * Map a [Covid19StatsView] to and from a [Covid19StatsViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class Covid19StatsMapper @Inject constructor() : Mapper<Covid19StatsViewModel, Covid19StatsView> {

    /**
     * Map a [Covid19StatsView] instance to a [Covid19StatsViewModel] instance
     */
    override fun mapToViewModel(type: Covid19StatsView): Covid19StatsViewModel {
        return Covid19StatsViewModel(type.state, type.country, type.countryCode,
                type.lastUpdate, type.confirmed, type.deaths, type.recovered)
    }

}