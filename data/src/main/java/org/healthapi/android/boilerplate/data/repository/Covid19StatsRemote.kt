package org.healthapi.android.boilerplate.data.repository

import io.reactivex.Single
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface Covid19StatsRemote {

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    fun getStats(): Single<List<Covid19StatsEntity>>

}