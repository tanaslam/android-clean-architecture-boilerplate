package org.healthapi.android.boilerplate.cache.mapper

import org.healthapi.android.boilerplate.cache.model.CachedCovid19Stats
import org.healthapi.android.boilerplate.cache.test.factory.BufferooFactory
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class Covid19StatsEntityMapperTest {

    private lateinit var covid19StatsEntityMapper: Covid19StatsEntityMapper

    @Before
    fun setUp() {
        covid19StatsEntityMapper = Covid19StatsEntityMapper()
    }

    @Test
    fun mapToCachedMapsData() {
        val bufferooEntity = BufferooFactory.makeBufferooEntity()
        val cachedBufferoo = covid19StatsEntityMapper.mapToCached(bufferooEntity)

        assertBufferooDataEquality(bufferooEntity, cachedBufferoo)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedBufferoo = BufferooFactory.makeCachedBufferoo()
        val bufferooEntity = covid19StatsEntityMapper.mapFromCached(cachedBufferoo)

        assertBufferooDataEquality(bufferooEntity, cachedBufferoo)
    }

    private fun assertBufferooDataEquality(covid19StatsEntity: Covid19StatsEntity,
                                           cachedCovid19Stats: CachedCovid19Stats) {
        assertEquals(covid19StatsEntity.state, cachedCovid19Stats.state)
        assertEquals(covid19StatsEntity.country, cachedCovid19Stats.country)
        assertEquals(covid19StatsEntity.countryCode, cachedCovid19Stats.countryCode)
        assertEquals(covid19StatsEntity.confirmed, cachedCovid19Stats.confirmed)
        assertEquals(covid19StatsEntity.deaths, cachedCovid19Stats.deaths)
        assertEquals(covid19StatsEntity.recovered, cachedCovid19Stats.recovered)
    }

}