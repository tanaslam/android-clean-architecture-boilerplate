package org.healthapi.android.boilerplate.data.source

import org.healthapi.android.boilerplate.data.repository.Covid19StatsCache
import org.healthapi.android.boilerplate.data.repository.Covid19StatsDataStore
import javax.inject.Inject

/**
 * Create an instance of a BufferooDataStore
 */
open class BufferooDataStoreFactory @Inject constructor(
        private val cache: Covid19StatsCache,
        private val cacheDataStore: Covid19StatsCacheDataStore,
        private val remoteDataStore: Covid19StatsRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): Covid19StatsDataStore {
        if (cache.isCached() && !cache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): Covid19StatsDataStore {
        return cacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): Covid19StatsDataStore {
        return remoteDataStore
    }

}