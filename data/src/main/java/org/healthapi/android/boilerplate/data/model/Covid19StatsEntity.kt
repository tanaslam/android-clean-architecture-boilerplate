package org.healthapi.android.boilerplate.data.model

/**
 * Representation for a [Covid19StatsEntity] fetched from an external layer data source
 */
data class Covid19StatsEntity(val state: String, val country: String, val countryCode: String,
                              val lastUpdate: Long, val confirmed: Long, val deaths: Long, val recovered: Long)