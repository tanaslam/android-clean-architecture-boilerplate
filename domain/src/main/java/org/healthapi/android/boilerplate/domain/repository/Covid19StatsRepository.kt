package org.healthapi.android.boilerplate.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import org.healthapi.android.boilerplate.domain.model.Covid19Stats

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface Covid19StatsRepository {

    fun clearStats(): Completable

    fun saveStats(covid19Stats: List<Covid19Stats>): Completable

    fun getStats(): Single<List<Covid19Stats>>

}