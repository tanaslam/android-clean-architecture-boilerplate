package org.healthapi.android.boilerplate.ui

import org.healthapi.android.boilerplate.ui.mapper.Covid19StatsMapper
import org.healthapi.android.boilerplate.ui.test.factory.Covid19StatsFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class Covid19StatsMapperTest {

    private lateinit var covid19StatsMapper: Covid19StatsMapper

    @Before
    fun setUp() {
        covid19StatsMapper = Covid19StatsMapper()
    }

    @Test
    fun mapToViewMapsData() {
        val bufferooView = Covid19StatsFactory.createStatsView()
        val bufferooViewModel = covid19StatsMapper.mapToViewModel(bufferooView)

        assertEquals(bufferooView.state, bufferooViewModel.state)
        assertEquals(bufferooView.country, bufferooViewModel.country)
        assertEquals(bufferooView.countryCode, bufferooViewModel.countryCode)
        assertEquals(bufferooView.lastUpdate, bufferooViewModel.lastUpdate)
        assertEquals(bufferooView.confirmed, bufferooViewModel.confirmed)
        assertEquals(bufferooView.deaths, bufferooViewModel.deaths)
        assertEquals(bufferooView.recovered, bufferooViewModel.recovered)
    }

}