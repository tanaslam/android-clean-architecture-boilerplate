package org.healthapi.android.boilerplate.cache.test.factory

import org.healthapi.android.boilerplate.cache.model.CachedCovid19Stats
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.cache.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Bufferoo related instances
 */
class BufferooFactory {

    companion object Factory {

        fun makeCachedBufferoo(): CachedCovid19Stats {
            return CachedCovid19Stats(randomUuid(), randomUuid(), randomUuid())
        }

        fun makeBufferooEntity(): Covid19StatsEntity {
            return Covid19StatsEntity(randomUuid(), randomUuid(), randomUuid())
        }

        fun makeBufferooEntityList(count: Int): List<Covid19StatsEntity> {
            val bufferooEntities = mutableListOf<Covid19StatsEntity>()
            repeat(count) {
                bufferooEntities.add(makeBufferooEntity())
            }
            return bufferooEntities
        }

        fun makeCachedBufferooList(count: Int): List<CachedCovid19Stats> {
            val cachedBufferoos = mutableListOf<CachedCovid19Stats>()
            repeat(count) {
                cachedBufferoos.add(makeCachedBufferoo())
            }
            return cachedBufferoos
        }

    }

}