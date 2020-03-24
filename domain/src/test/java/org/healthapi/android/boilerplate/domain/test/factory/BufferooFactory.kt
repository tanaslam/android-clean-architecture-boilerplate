package org.healthapi.android.boilerplate.domain.test.factory

import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import org.healthapi.android.boilerplate.domain.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Bufferoo related instances
 */
class BufferooFactory {

    companion object Factory {

        fun makeBufferooList(count: Int): List<Covid19Stats> {
            val bufferoos = mutableListOf<Covid19Stats>()
            repeat(count) {
                bufferoos.add(makeBufferoo())
            }
            return bufferoos
        }

        fun makeBufferoo(): Covid19Stats {
            return Covid19Stats(randomUuid(), randomUuid(), randomUuid())
        }

    }

}