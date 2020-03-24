package org.healthapi.android.boilerplate.cache.db.mapper

import android.content.ContentValues
import android.database.Cursor
import org.healthapi.android.boilerplate.cache.db.Db
import org.healthapi.android.boilerplate.cache.model.CachedCovid19Stats
import javax.inject.Inject

/**
 * Maps a [CachedCovid19Stats] instance to a database entity.
 */
class Covid19StatsMapper @Inject constructor() : ModelDbMapper<CachedCovid19Stats> {

    /**
     * Construct an instance of [ContentValues] using the given [CachedCovid19Stats]
     */
    override fun toContentValues(model: CachedCovid19Stats): ContentValues {
        val values = ContentValues()
        values.put(Db.Covid19StatsTable.STATE, model.state)
        values.put(Db.Covid19StatsTable.COUNTRY, model.country)
        values.put(Db.Covid19StatsTable.COUNTRY_CODE, model.countryCode)
        values.put(Db.Covid19StatsTable.LAST_UPDATED, model.lastUpdate)
        values.put(Db.Covid19StatsTable.CONFIRMED, model.confirmed)
        values.put(Db.Covid19StatsTable.DEATHS, model.deaths)
        values.put(Db.Covid19StatsTable.RECORVED, model.recovered)
        return values
    }

    /**
     * Parse the cursor creating a [CachedCovid19Stats] instance.
     */
    override fun parseCursor(cursor: Cursor): CachedCovid19Stats {
        val state = cursor.getString(cursor.getColumnIndexOrThrow(Db.Covid19StatsTable.STATE))
        val country = cursor.getString(cursor.getColumnIndexOrThrow(Db.Covid19StatsTable.COUNTRY))
        val contryCode = cursor.getString(cursor.getColumnIndexOrThrow(Db.Covid19StatsTable.COUNTRY_CODE))
        val lastUpdated = cursor.getLong(cursor.getColumnIndexOrThrow(Db.Covid19StatsTable.LAST_UPDATED))
        val confirmed = cursor.getLong(cursor.getColumnIndexOrThrow(Db.Covid19StatsTable.CONFIRMED))
        val deaths = cursor.getLong(cursor.getColumnIndexOrThrow(Db.Covid19StatsTable.DEATHS))
        val recovered = cursor.getLong(cursor.getColumnIndexOrThrow(Db.Covid19StatsTable.RECORVED))
        return CachedCovid19Stats(state, country, contryCode, lastUpdated, confirmed, deaths, recovered)
    }

}