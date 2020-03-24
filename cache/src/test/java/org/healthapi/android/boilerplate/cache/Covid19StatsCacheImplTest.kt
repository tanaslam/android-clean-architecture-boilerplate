package org.healthapi.android.boilerplate.cache

import org.healthapi.android.boilerplate.cache.db.Db
import org.healthapi.android.boilerplate.cache.db.DbOpenHelper
import org.healthapi.android.boilerplate.cache.db.mapper.Covid19StatsMapper
import org.healthapi.android.boilerplate.cache.mapper.Covid19StatsEntityMapper
import org.healthapi.android.boilerplate.cache.model.CachedCovid19Stats
import org.healthapi.android.boilerplate.cache.test.factory.BufferooFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class Covid19StatsCacheImplTest {

    private var entityMapper = Covid19StatsEntityMapper()
    private var mapper = Covid19StatsMapper()
    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)

    private val databaseHelper = Covid19StatsCacheImpl(DbOpenHelper(RuntimeEnvironment.application),
            entityMapper, mapper, preferencesHelper)

    @Before
    fun setup() {
        databaseHelper.getDatabase().rawQuery("DELETE FROM " + Db.Covid19StatsTable.TABLE_NAME, null)
    }

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clearStats().test()
        testObserver.assertComplete()
    }

    //<editor-fold desc="Save Bufferoos">
    @Test
    fun saveBufferoosCompletes() {
        val bufferooEntities = BufferooFactory.makeBufferooEntityList(2)

        val testObserver = databaseHelper.saveStats(bufferooEntities).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveBufferoosSavesData() {
        val bufferooCount = 2
        val bufferooEntities = BufferooFactory.makeBufferooEntityList(bufferooCount)

        databaseHelper.saveStats(bufferooEntities).test()
        checkNumRowsInBufferoosTable(bufferooCount)
    }
    //</editor-fold>

    //<editor-fold desc="Get Bufferoos">
    @Test
    fun getBufferoosCompletes() {
        val testObserver = databaseHelper.getStats().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBufferoosReturnsData() {
        val bufferooEntities = BufferooFactory.makeBufferooEntityList(2)
        val cachedBufferoos = mutableListOf<CachedCovid19Stats>()
        bufferooEntities.forEach {
            cachedBufferoos.add(entityMapper.mapToCached(it))
        }
        insertBufferoos(cachedBufferoos)

        val testObserver = databaseHelper.getStats().test()
        testObserver.assertValue(bufferooEntities)
    }
    //</editor-fold>

    private fun insertBufferoos(cachedCovid19Stats: List<CachedCovid19Stats>) {
        val database = databaseHelper.getDatabase()
        cachedCovid19Stats.forEach {
            database.insertOrThrow(Db.Covid19StatsTable.TABLE_NAME, null,
                    mapper.toContentValues(it))
        }
    }

    private fun checkNumRowsInBufferoosTable(expectedRows: Int) {
        val bufferoosCursor = databaseHelper.getDatabase().query(Db.Covid19StatsTable.TABLE_NAME,
                null, null, null, null, null, null)
        bufferoosCursor.moveToFirst()
        val numberOfRows = bufferoosCursor.count
        bufferoosCursor.close()
        assertEquals(expectedRows, numberOfRows)
    }

}