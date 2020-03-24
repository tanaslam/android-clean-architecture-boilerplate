package org.healthapi.android.boilerplate.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity

/**
 * Interface defining methods for the data operations related to Bufferroos.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface Covid19StatsDataStore {

    fun clearStats(): Completable

    fun saveStats(covid19Stats: List<Covid19StatsEntity>): Completable

    fun getStats(): Single<List<Covid19StatsEntity>>

}