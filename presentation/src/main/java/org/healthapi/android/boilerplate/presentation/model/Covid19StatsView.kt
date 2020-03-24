package org.healthapi.android.boilerplate.presentation.model

/**
 * Representation for a [Covid19StatsView] instance for this layers Model representation
 */
class Covid19StatsView(val state: String, val country: String, val countryCode: String,
                       val lastUpdate: Long, val confirmed: Long, val deaths: Long, val recovered: Long)