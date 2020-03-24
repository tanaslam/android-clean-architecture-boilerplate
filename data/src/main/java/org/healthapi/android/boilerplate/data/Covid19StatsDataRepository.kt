package org.healthapi.android.boilerplate.data

import io.reactivex.Completable
import io.reactivex.Single
import org.healthapi.android.boilerplate.data.mapper.Covid19StatsDataMapper
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.data.source.BufferooDataStoreFactory
import org.healthapi.android.boilerplate.data.source.Covid19StatsRemoteDataStore
import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import org.healthapi.android.boilerplate.domain.repository.Covid19StatsRepository
import javax.inject.Inject

/**
 * Provides an implementation of the [Covid19StatsRepository] interface for communicating to and from
 * data sources
 */
class Covid19StatsDataRepository @Inject constructor(private val factory: BufferooDataStoreFactory,
                                                     private val covid19StatsDataMapper: Covid19StatsDataMapper) :
        Covid19StatsRepository {

    override fun clearStats(): Completable {
        return factory.retrieveCacheDataStore().clearStats()
    }

    override fun saveStats(covid19Stats: List<Covid19Stats>): Completable {
        val bufferooEntities = covid19Stats.map { covid19StatsDataMapper.mapToEntity(it) }
        return saveBufferooEntities(bufferooEntities)
    }

    private fun saveBufferooEntities(covid19Stats: List<Covid19StatsEntity>): Completable {
        return factory.retrieveCacheDataStore().saveStats(covid19Stats)
    }

    override fun getStats(): Single<List<Covid19Stats>> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.getStats()
                .flatMap {
                    if (dataStore is Covid19StatsRemoteDataStore) {
                        saveBufferooEntities(it).toSingle { it }
                    } else {
                        Single.just(it)
                    }
                }
                .map { list ->
                    list.map { listItem ->
                        covid19StatsDataMapper.mapFromEntity(listItem)
                    }
                }
    }

}