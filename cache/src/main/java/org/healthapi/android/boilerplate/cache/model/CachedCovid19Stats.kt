package org.healthapi.android.boilerplate.cache.model

/**
 * Model used solely for the caching of a bufferroo
 */
data class CachedCovid19Stats(val state: String, val country: String, val countryCode: String,
                              val lastUpdate: Long, val confirmed: Long, val deaths: Long, val recovered: Long)