package org.healthapi.android.boilerplate.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.data.repository.Covid19StatsRemote
import org.healthapi.android.boilerplate.data.test.factory.Covid19StatsFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class Covid19StatsRemoteDataStoreTest {

    private lateinit var bufferooRemoteDataStore: Covid19StatsRemoteDataStore

    private lateinit var covid19StatsRemote: Covid19StatsRemote

    @Before
    fun setUp() {
        covid19StatsRemote = mock()
        bufferooRemoteDataStore = Covid19StatsRemoteDataStore(covid19StatsRemote)
    }

    //<editor-fold desc="Clear Bufferoos">
    @Test(expected = UnsupportedOperationException::class)
    fun clearBufferoosThrowsException() {
        bufferooRemoteDataStore.clearStats().test()
    }
    //</editor-fold>

    //<editor-fold desc="Save Bufferoos">
    @Test(expected = UnsupportedOperationException::class)
    fun saveBufferoosThrowsException() {
        bufferooRemoteDataStore.saveStats(Covid19StatsFactory.makeCovid19StatsEntityList(2)).test()
    }
    //</editor-fold>

    //<editor-fold desc="Get Bufferoos">
    @Test
    fun getBufferoosCompletes() {
        stubBufferooCacheGetBufferoos(Single.just(Covid19StatsFactory.makeCovid19StatsEntityList(2)))
        val testObserver = covid19StatsRemote.getStats().test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubBufferooCacheGetBufferoos(single: Single<List<Covid19StatsEntity>>) {
        whenever(covid19StatsRemote.getStats())
                .thenReturn(single)
    }
    //</editor-fold>

}