package org.healthapi.android.boilerplate.presentation.test.factory

import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import org.healthapi.android.boilerplate.presentation.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Bufferoo related instances
 */
class BufferooFactory {

    companion object Factory {

        fun makeBufferooList(count: Int): List<Covid19Stats> {
            val bufferoos = mutableListOf<Covid19Stats>()
            repeat(count) {
                bufferoos.add(makeBufferooModel())
            }
            return bufferoos
        }

        fun makeBufferooModel(): Covid19Stats {
            return Covid19Stats(randomUuid(), randomUuid(), randomUuid())
        }

    }

}