package org.healthapi.android.boilerplate.data.source

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Single
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.data.repository.Covid19StatsCache
import org.healthapi.android.boilerplate.data.test.factory.Covid19StatsFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class Covid19StatsCacheDataStoreTest {

    private lateinit var bufferooCacheDataStore: Covid19StatsCacheDataStore

    private lateinit var covid19StatsCache: Covid19StatsCache

    @Before
    fun setUp() {
        covid19StatsCache = mock()
        bufferooCacheDataStore = Covid19StatsCacheDataStore(covid19StatsCache)
    }

    //<editor-fold desc="Clear Bufferoos">
    @Test
    fun clearBufferoosCompletes() {
        stubBufferooCacheClearBufferoos(Completable.complete())
        val testObserver = bufferooCacheDataStore.clearStats().test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Save Bufferoos">
    @Test
    fun saveBufferoosCompletes() {
        stubBufferooCacheSaveBufferoos(Completable.complete())
        val testObserver = bufferooCacheDataStore.saveStats(
                Covid19StatsFactory.makeCovid19StatsEntityList(2)).test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Get Bufferoos">
    @Test
    fun getBufferoosCompletes() {
        stubBufferooCacheGetBufferoos(Single.just(Covid19StatsFactory.makeCovid19StatsEntityList(2)))
        val testObserver = bufferooCacheDataStore.getStats().test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubBufferooCacheSaveBufferoos(completable: Completable) {
        whenever(covid19StatsCache.saveStats(any()))
                .thenReturn(completable)
    }

    private fun stubBufferooCacheGetBufferoos(single: Single<List<Covid19StatsEntity>>) {
        whenever(covid19StatsCache.getStats())
                .thenReturn(single)
    }

    private fun stubBufferooCacheClearBufferoos(completable: Completable) {
        whenever(covid19StatsCache.clearStats())
                .thenReturn(completable)
    }
    //</editor-fold>

}