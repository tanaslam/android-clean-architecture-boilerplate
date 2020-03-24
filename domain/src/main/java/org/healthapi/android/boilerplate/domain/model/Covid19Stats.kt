package org.healthapi.android.boilerplate.domain.model

/**
 * Representation for a [Covid19Stats] fetched from an external layer data source
 */
data class Covid19Stats(val state: String, val country: String, val countryCode: String,
                        val lastUpdate: Long, val confirmed: Long, val deaths: Long, val recovered: Long)