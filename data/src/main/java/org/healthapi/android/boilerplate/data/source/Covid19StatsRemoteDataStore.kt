package org.healthapi.android.boilerplate.data.source

import io.reactivex.Completable
import io.reactivex.Single
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.data.repository.Covid19StatsDataStore
import org.healthapi.android.boilerplate.data.repository.Covid19StatsRemote
import javax.inject.Inject

/**
 * Implementation of the [Covid19StatsDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class Covid19StatsRemoteDataStore @Inject constructor(private val covid19StatsRemote: Covid19StatsRemote) :
        Covid19StatsDataStore {

    override fun clearStats(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveStats(covid19Stats: List<Covid19StatsEntity>): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [Covid19StatsEntity] instances from the API
     */
    override fun getStats(): Single<List<Covid19StatsEntity>> {
        return covid19StatsRemote.getStats()
    }

}