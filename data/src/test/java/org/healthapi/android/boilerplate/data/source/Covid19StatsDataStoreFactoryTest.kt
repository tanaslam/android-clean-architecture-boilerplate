package org.healthapi.android.boilerplate.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.healthapi.android.boilerplate.data.repository.Covid19StatsCache
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class Covid19StatsDataStoreFactoryTest {

    private lateinit var bufferooDataStoreFactory: BufferooDataStoreFactory

    private lateinit var covid19StatsCache: Covid19StatsCache
    private lateinit var bufferooCacheDataStore: Covid19StatsCacheDataStore
    private lateinit var bufferooRemoteDataStore: Covid19StatsRemoteDataStore

    @Before
    fun setUp() {
        covid19StatsCache = mock()
        bufferooCacheDataStore = mock()
        bufferooRemoteDataStore = mock()
        bufferooDataStoreFactory = BufferooDataStoreFactory(covid19StatsCache,
                bufferooCacheDataStore, bufferooRemoteDataStore)
    }

    //<editor-fold desc="Retrieve Data Store">
    @Test
    fun retrieveDataStoreWhenNotCachedReturnsRemoteDataStore() {
        stubBufferooCacheIsCached(false)
        val bufferooDataStore = bufferooDataStoreFactory.retrieveDataStore()
        assert(bufferooDataStore is Covid19StatsRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreWhenCacheExpiredReturnsRemoteDataStore() {
        stubBufferooCacheIsCached(true)
        stubBufferooCacheIsExpired(true)
        val bufferooDataStore = bufferooDataStoreFactory.retrieveDataStore()
        assert(bufferooDataStore is Covid19StatsRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreReturnsCacheDataStore() {
        stubBufferooCacheIsCached(true)
        stubBufferooCacheIsExpired(false)
        val bufferooDataStore = bufferooDataStoreFactory.retrieveDataStore()
        assert(bufferooDataStore is Covid19StatsCacheDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Retrieve Remote Data Store">
    @Test
    fun retrieveRemoteDataStoreReturnsRemoteDataStore() {
        val bufferooDataStore = bufferooDataStoreFactory.retrieveRemoteDataStore()
        assert(bufferooDataStore is Covid19StatsRemoteDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Retrieve Cache Data Store">
    @Test
    fun retrieveCacheDataStoreReturnsCacheDataStore() {
        val bufferooDataStore = bufferooDataStoreFactory.retrieveCacheDataStore()
        assert(bufferooDataStore is Covid19StatsCacheDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubBufferooCacheIsCached(isCached: Boolean) {
        whenever(covid19StatsCache.isCached())
                .thenReturn(isCached)
    }

    private fun stubBufferooCacheIsExpired(isExpired: Boolean) {
        whenever(covid19StatsCache.isExpired())
                .thenReturn(isExpired)
    }
    //</editor-fold>

}