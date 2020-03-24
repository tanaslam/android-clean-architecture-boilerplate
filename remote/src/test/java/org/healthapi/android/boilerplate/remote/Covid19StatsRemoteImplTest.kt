package org.healthapi.android.boilerplate.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.remote.mapper.Covid19StatsEntityMapper
import org.healthapi.android.boilerplate.remote.test.factory.Covid19StatsFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class Covid19StatsRemoteImplTest {

    private lateinit var entityMapper: Covid19StatsEntityMapper
    private lateinit var covid19StatsService: Covid19StatsService

    private lateinit var covid19StatsRemoteImpl: Covid19StatsRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        covid19StatsService = mock()
        covid19StatsRemoteImpl = Covid19StatsRemoteImpl(covid19StatsService, entityMapper)
    }

    //<editor-fold desc="Get Bufferoos">
    @Test
    fun getBufferoosCompletes() {
        stubCovid19StatsServiceGetStats(Single.just(Covid19StatsFactory.makeResponse()))
        val testObserver = covid19StatsRemoteImpl.getStats().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBufferoosReturnsData() {
        val response = Covid19StatsFactory.makeResponse()
        stubCovid19StatsServiceGetStats(Single.just(listOf(response)))
        val entities = mutableListOf<Covid19StatsEntity>()
        response.forEach {
            entities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = covid19StatsRemoteImpl.getStats().test()
        testObserver.assertValue(entities)
    }

    private fun stubCovid19StatsServiceGetStats(single: Single<List<Covid19StatsService.Response>>) {
        whenever(covid19StatsService.getStatsAll())
                .thenReturn(single)
    }
}