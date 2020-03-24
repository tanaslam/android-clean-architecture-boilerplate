package org.healthapi.android.boilerplate.remote.test.factory

import org.healthapi.android.boilerplate.remote.test.factory.DataFactory.Factory.randomUuid
import org.healthapi.android.boilerplate.remote.Covid19StatsService
import org.healthapi.android.boilerplate.remote.model.Covid19StatsModel
import java.util.*

/**
 * Factory class for Bufferoo related instances
 */
class Covid19StatsFactory {

    companion object Factory {

        fun makeResponse(): List<Covid19StatsService.Response> {
            val response = Covid19StatsService.Response()
                response.stat
            return createCovid19StatsModelList(5)
        }

        private fun createCovid19StatsModelList(count: Int): List<Covid19StatsModel> {
            val list = mutableListOf<Covid19StatsModel>()
            repeat(count) {
                list.add(createTestModel())
            }
            return list
        }

        fun createTestModel(): Covid19StatsModel {
            return Covid19StatsModel(randomUuid(), randomUuid(), randomUuid().substring(0..1),
                    Date().time, Math.random().toLong(), Math.random().toLong(), Math.random().toLong())
        }

    }

}