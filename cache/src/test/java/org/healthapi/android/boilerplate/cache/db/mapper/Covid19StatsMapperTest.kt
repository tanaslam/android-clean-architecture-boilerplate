package org.healthapi.android.boilerplate.cache.db.mapper

import android.database.Cursor
import org.healthapi.android.boilerplate.cache.BuildConfig
import org.healthapi.android.boilerplate.cache.db.Db
import org.healthapi.android.boilerplate.cache.db.DbOpenHelper
import org.healthapi.android.boilerplate.cache.model.CachedCovid19Stats
import org.healthapi.android.boilerplate.cache.test.DefaultConfig
import org.healthapi.android.boilerplate.cache.test.factory.BufferooFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [DefaultConfig.EMULATE_SDK])
class Covid19StatsMapperTest {

    private lateinit var covid19StatsMapper: Covid19StatsMapper
    private val database = DbOpenHelper(RuntimeEnvironment.application).writableDatabase

    @Before
    fun setUp() {
        covid19StatsMapper = Covid19StatsMapper()
    }

    @Test
    fun parseCursorMapsData() {
        val cachedBufferoo = BufferooFactory.makeCachedBufferoo()
        insertCachedBufferoo(cachedBufferoo)

        val cursor = retrieveCachedBufferooCursor()
        assertEquals(cachedBufferoo, covid19StatsMapper.parseCursor(cursor))
    }

    private fun retrieveCachedBufferooCursor(): Cursor {
        val cursor = database.rawQuery("SELECT * FROM " + Db.Covid19StatsTable.TABLE_NAME, null)
        cursor.moveToFirst()
        return cursor
    }

    private fun insertCachedBufferoo(cachedCovid19Stats: CachedCovid19Stats) {
        database.insertOrThrow(Db.Covid19StatsTable.TABLE_NAME, null,
                covid19StatsMapper.toContentValues(cachedCovid19Stats))
    }

}