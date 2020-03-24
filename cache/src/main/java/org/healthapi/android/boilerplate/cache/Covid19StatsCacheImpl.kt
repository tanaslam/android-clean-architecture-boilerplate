package org.healthapi.android.boilerplate.cache

import android.database.sqlite.SQLiteDatabase
import io.reactivex.Completable
import io.reactivex.Single
import org.healthapi.android.boilerplate.cache.db.Db
import org.healthapi.android.boilerplate.cache.db.DbOpenHelper
import org.healthapi.android.boilerplate.cache.db.constants.BufferooConstants
import org.healthapi.android.boilerplate.cache.db.mapper.Covid19StatsMapper
import org.healthapi.android.boilerplate.cache.mapper.Covid19StatsEntityMapper
import org.healthapi.android.boilerplate.cache.model.CachedCovid19Stats
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.data.repository.Covid19StatsCache
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Bufferoo instances. This class implements the
 * [Covid19StatsCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class Covid19StatsCacheImpl @Inject constructor(dbOpenHelper: DbOpenHelper,
                                                private val entityMapper: Covid19StatsEntityMapper,
                                                private val mapper: Covid19StatsMapper,
                                                private val preferencesHelper: PreferencesHelper):
        Covid19StatsCache {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    private var database: SQLiteDatabase = dbOpenHelper.writableDatabase

    /**
     * Retrieve an instance from the database, used for tests
     */
    internal fun getDatabase(): SQLiteDatabase {
        return database
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clearStats(): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                database.delete(Db.Covid19StatsTable.TABLE_NAME, null, null)
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    /**
     * Save the given list of [Covid19StatsEntity] instances to the database.
     */
    override fun saveStats(covid19Stats: List<Covid19StatsEntity>): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                covid19Stats.forEach {
                    saveBufferoo(entityMapper.mapToCached(it))
                }
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [Covid19StatsEntity] instances from the database.
     */
    override fun getStats(): Single<List<Covid19StatsEntity>> {
        return Single.defer<List<Covid19StatsEntity>> {
            val updatesCursor = database.rawQuery(BufferooConstants.QUERY_GET_ALL_BUFFEROOS, null)
            val bufferoos = mutableListOf<Covid19StatsEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedBufferoo = mapper.parseCursor(updatesCursor)
                bufferoos.add(entityMapper.mapFromCached(cachedBufferoo))
            }

            updatesCursor.close()
            Single.just<List<Covid19StatsEntity>>(bufferoos)
        }
    }

    /**
     * Helper method for saving a [CachedCovid19Stats] instance to the database.
     */
    private fun saveBufferoo(cachedCovid19Stats: CachedCovid19Stats) {
        database.insert(Db.Covid19StatsTable.TABLE_NAME, null, mapper.toContentValues(cachedCovid19Stats))
    }

    /**
     * Checked whether there are instances of [CachedCovid19Stats] stored in the cache
     */
    override fun isCached(): Boolean {
        return database.rawQuery(BufferooConstants.QUERY_GET_ALL_BUFFEROOS, null).count > 0
    }

    /**
     * Set a point in time at when the cache was last updated
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

}