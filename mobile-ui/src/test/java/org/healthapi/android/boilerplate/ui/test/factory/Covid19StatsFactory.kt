package org.healthapi.android.boilerplate.ui.test.factory

import org.healthapi.android.boilerplate.presentation.model.Covid19StatsView
import org.healthapi.android.boilerplate.remote.test.factory.DataFactory
import java.util.*

/**
 * Factory class for Bufferoo related instances
 */
class Covid19StatsFactory {

    companion object Factory {

        fun createStatsView(): Covid19StatsView {
            return Covid19StatsView(DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid().substring(0..1),
                    Date().time, Math.random().toLong(), Math.random().toLong(), Math.random().toLong())
        }

    }

}