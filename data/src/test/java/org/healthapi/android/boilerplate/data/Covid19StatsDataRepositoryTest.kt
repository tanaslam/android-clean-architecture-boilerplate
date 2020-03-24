package org.healthapi.android.boilerplate.data

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.Single
import org.healthapi.android.boilerplate.data.mapper.Covid19StatsDataMapper
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.data.repository.Covid19StatsDataStore
import org.healthapi.android.boilerplate.data.source.Covid19StatsCacheDataStore
import org.healthapi.android.boilerplate.data.source.BufferooDataStoreFactory
import org.healthapi.android.boilerplate.data.source.Covid19StatsRemoteDataStore
import org.healthapi.android.boilerplate.data.test.factory.Covid19StatsFactory
import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class Covid19StatsDataRepositoryTest {

    private lateinit var bufferooDataRepository: Covid19StatsDataRepository

    private lateinit var bufferooDataStoreFactory: BufferooDataStoreFactory
    private lateinit var covid19StatsDataMapper: Covid19StatsDataMapper
    private lateinit var bufferooCacheDataStore: Covid19StatsCacheDataStore
    private lateinit var bufferooRemoteDataStore: Covid19StatsRemoteDataStore

    @Before
    fun setUp() {
        bufferooDataStoreFactory = mock()
        covid19StatsDataMapper = mock()
        bufferooCacheDataStore = mock()
        bufferooRemoteDataStore = mock()
        bufferooDataRepository = Covid19StatsDataRepository(bufferooDataStoreFactory, covid19StatsDataMapper)
        stubBufferooDataStoreFactoryRetrieveCacheDataStore()
        stubBufferooDataStoreFactoryRetrieveRemoteDataStore()
    }

    //<editor-fold desc="Clear Bufferoos">
    @Test
    fun clearBufferoosCompletes() {
        stubBufferooCacheClearBufferoos(Completable.complete())
        val testObserver = bufferooDataRepository.clearStats().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearBufferoosCallsCacheDataStore() {
        stubBufferooCacheClearBufferoos(Completable.complete())
        bufferooDataRepository.clearStats().test()
        verify(bufferooCacheDataStore).clearStats()
    }

    @Test
    fun clearBufferoosNeverCallsRemoteDataStore() {
        stubBufferooCacheClearBufferoos(Completable.complete())
        bufferooDataRepository.clearStats().test()
        verify(bufferooRemoteDataStore, never()).clearStats()
    }
    //</editor-fold>

    //<editor-fold desc="Save Bufferoos">
    @Test
    fun saveBufferoosCompletes() {
        stubBufferooCacheSaveBufferoos(Completable.complete())
        val testObserver = bufferooDataRepository.saveStats(
                Covid19StatsFactory.makeBufferooList(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveBufferoosCallsCacheDataStore() {
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveStats(Covid19StatsFactory.makeBufferooList(2)).test()
        verify(bufferooCacheDataStore).saveStats(any())
    }

    @Test
    fun saveBufferoosNeverCallsRemoteDataStore() {
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveStats(Covid19StatsFactory.makeBufferooList(2)).test()
        verify(bufferooRemoteDataStore, never()).saveStats(any())
    }
    //</editor-fold>

    //<editor-fold desc="Get Bufferoos">
    @Test
    fun getBufferoosCompletes() {
        stubBufferooDataStoreFactoryRetrieveDataStore(bufferooCacheDataStore)
        stubBufferooCacheDataStoreGetBufferoos(Single.just(
                Covid19StatsFactory.makeCovid19StatsEntityList(2)))
        val testObserver = bufferooDataRepository.getStats().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBufferoosReturnsData() {
        stubBufferooDataStoreFactoryRetrieveDataStore(bufferooCacheDataStore)
        val bufferoos = Covid19StatsFactory.makeBufferooList(2)
        val bufferooEntities = Covid19StatsFactory.makeCovid19StatsEntityList(2)
        bufferoos.forEachIndexed { index, bufferoo ->
            stubBufferooMapperMapFromEntity(bufferooEntities[index], bufferoo) }
        stubBufferooCacheDataStoreGetBufferoos(Single.just(bufferooEntities))

        val testObserver = bufferooDataRepository.getStats().test()
        testObserver.assertValue(bufferoos)
    }

    @Test
    fun getBufferoosSavesBufferoosWhenFromCacheDataStore() {
        stubBufferooDataStoreFactoryRetrieveDataStore(bufferooCacheDataStore)
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveStats(Covid19StatsFactory.makeBufferooList(2)).test()
        verify(bufferooCacheDataStore).saveStats(any())
    }

    @Test
    fun getBufferoosNeverSavesBufferoosWhenFromRemoteDataStore() {
        stubBufferooDataStoreFactoryRetrieveDataStore(bufferooRemoteDataStore)
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveStats(Covid19StatsFactory.makeBufferooList(2)).test()
        verify(bufferooRemoteDataStore, never()).saveStats(any())
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubBufferooCacheSaveBufferoos(completable: Completable) {
        whenever(bufferooCacheDataStore.saveStats(any()))
                .thenReturn(completable)
    }

    private fun stubBufferooCacheDataStoreGetBufferoos(single: Single<List<Covid19StatsEntity>>) {
        whenever(bufferooCacheDataStore.getStats())
                .thenReturn(single)
    }

    private fun stubBufferooRemoteDataStoreGetBufferoos(single: Single<List<Covid19StatsEntity>>) {
        whenever(bufferooRemoteDataStore.getStats())
                .thenReturn(single)
    }

    private fun stubBufferooCacheClearBufferoos(completable: Completable) {
        whenever(bufferooCacheDataStore.clearStats())
                .thenReturn(completable)
    }

    private fun stubBufferooDataStoreFactoryRetrieveCacheDataStore() {
        whenever(bufferooDataStoreFactory.retrieveCacheDataStore())
                .thenReturn(bufferooCacheDataStore)
    }

    private fun stubBufferooDataStoreFactoryRetrieveRemoteDataStore() {
        whenever(bufferooDataStoreFactory.retrieveRemoteDataStore())
                .thenReturn(bufferooCacheDataStore)
    }

    private fun stubBufferooDataStoreFactoryRetrieveDataStore(dataStore: Covid19StatsDataStore) {
        whenever(bufferooDataStoreFactory.retrieveDataStore())
                .thenReturn(dataStore)
    }

    private fun stubBufferooMapperMapFromEntity(covid19StatsEntity: Covid19StatsEntity,
                                                covid19Stats: Covid19Stats) {
        whenever(covid19StatsDataMapper.mapFromEntity(covid19StatsEntity))
                .thenReturn(covid19Stats)
    }
    //</editor-fold>

}