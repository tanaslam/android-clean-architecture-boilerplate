package org.healthapi.android.boilerplate.remote.model

/**
 * Representation for a [Covid19StatsModel] fetched from the from heath-api rest API
 */
class Covid19StatsModel(val country: String, val country_code: String?,
                        val last_update: Long, val confirmed: Long, val deaths: Long, val recovered: Long)
