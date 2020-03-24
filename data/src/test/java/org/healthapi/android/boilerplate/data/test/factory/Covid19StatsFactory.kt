package org.healthapi.android.boilerplate.data.test.factory

import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.data.test.factory.DataFactory.Factory.randomUuid
import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import java.util.*

/**
 * Factory class for Bufferoo related instances
 */
class Covid19StatsFactory {

    companion object Factory {

        fun createCovid19StatsEntity(): Covid19StatsEntity {
            return Covid19StatsEntity(randomUuid(), randomUuid(), randomUuid().substring(0..1),
                    Date().time, Math.random().toLong(), Math.random().toLong(), Math.random().toLong())
        }

        fun makeCovid19Stats(): Covid19Stats {
            return Covid19Stats(randomUuid(), randomUuid(), randomUuid().substring(0..1),
                    Date().time, Math.random().toLong(), Math.random().toLong(), Math.random().toLong())
        }

        fun makeCovid19StatsEntityList(count: Int): List<Covid19StatsEntity> {
            val list = mutableListOf<Covid19StatsEntity>()
            repeat(count) {
                list.add(createCovid19StatsEntity())
            }
            return list
        }

        fun makeBufferooList(count: Int): List<Covid19Stats> {
            val list = mutableListOf<Covid19Stats>()
            repeat(count) {
                list.add(makeCovid19Stats())
            }
            return list
        }

    }

}