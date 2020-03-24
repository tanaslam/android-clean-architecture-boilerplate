package org.healthapi.android.boilerplate.data.source

import io.reactivex.Completable
import io.reactivex.Single
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.data.repository.Covid19StatsCache
import org.healthapi.android.boilerplate.data.repository.Covid19StatsDataStore
import javax.inject.Inject

/**
 * Implementation of the [Covid19StatsDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class Covid19StatsCacheDataStore @Inject constructor(private val covid19StatsCache: Covid19StatsCache) :
        Covid19StatsDataStore {

    /**
     * Clear all Bufferoos from the cache
     */
    override fun clearStats(): Completable {
        return covid19StatsCache.clearStats()
    }

    /**
     * Save a given [List] of [Covid19StatsEntity] instances to the cache
     */
    override fun saveStats(covid19Stats: List<Covid19StatsEntity>): Completable {
        return covid19StatsCache.saveStats(covid19Stats)
                .doOnComplete {
                    covid19StatsCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    /**
     * Retrieve a list of [Covid19StatsEntity] instance from the cache
     */
    override fun getStats(): Single<List<Covid19StatsEntity>> {
        return covid19StatsCache.getStats()
    }

}