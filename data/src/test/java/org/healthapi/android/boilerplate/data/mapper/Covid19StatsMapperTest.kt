package org.healthapi.android.boilerplate.data.mapper

import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.data.test.factory.Covid19StatsFactory
import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class Covid19StatsMapperTest {

    private lateinit var covid19StatsDataMapper: Covid19StatsDataMapper

    @Before
    fun setUp() {
        covid19StatsDataMapper = Covid19StatsDataMapper()
    }

    @Test
    fun mapFromEntityMapsData() {
        val bufferooEntity = Covid19StatsFactory.createCovid19StatsEntity()
        val bufferoo = covid19StatsDataMapper.mapFromEntity(bufferooEntity)

        assertBufferooDataEquality(bufferooEntity, bufferoo)
    }

    @Test
    fun mapToEntityMapsData() {
        val cachedBufferoo = Covid19StatsFactory.makeCovid19Stats()
        val bufferooEntity = covid19StatsDataMapper.mapToEntity(cachedBufferoo)

        assertBufferooDataEquality(bufferooEntity, cachedBufferoo)
    }

    private fun assertBufferooDataEquality(covid19StatsEntity: Covid19StatsEntity,
                                           covid19Stats: Covid19Stats) {
        assertEquals(covid19StatsEntity.state, covid19Stats.state)
        assertEquals(covid19StatsEntity.country, covid19Stats.country)
        assertEquals(covid19StatsEntity.countryCode, covid19Stats.countryCode)
        assertEquals(covid19StatsEntity.confirmed, covid19Stats.confirmed)
        assertEquals(covid19StatsEntity.deaths, covid19Stats.deaths)
        assertEquals(covid19StatsEntity.recovered, covid19Stats.recovered)
    }

}