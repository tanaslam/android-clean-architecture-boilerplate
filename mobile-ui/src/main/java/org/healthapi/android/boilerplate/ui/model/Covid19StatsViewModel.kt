package org.healthapi.android.boilerplate.ui.model

/**
 * Representation for a [Covid19StatsViewModel] fetched from an external layer data source
 */
class Covid19StatsViewModel(val state: String, val country: String, val countryCode: String,
                            val lastUpdate: Long, val confirmed: Long, val deaths: Long, val recovered: Long)