package org.healthapi.android.boilerplate.remote.mapper

import org.healthapi.android.boilerplate.remote.test.factory.Covid19StatsFactory
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
    fun mapFromRemoteMapsData() {
        val model = Covid19StatsFactory.createTestModel()
        val entity = covid19StatsEntityMapper.mapFromRemote(model)

        assertEquals(model.state, entity.state)
        assertEquals(model.country, entity.country)
        assertEquals(model.country_code, entity.countryCode)
        assertEquals(model.confirmed, entity.confirmed)
        assertEquals(model.deaths, entity.deaths)
        assertEquals(model.recovered, entity.recovered)
    }

}