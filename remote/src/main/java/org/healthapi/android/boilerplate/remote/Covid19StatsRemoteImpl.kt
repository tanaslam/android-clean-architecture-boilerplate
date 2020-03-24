package org.healthapi.android.boilerplate.remote

import io.reactivex.Single
import org.healthapi.android.boilerplate.data.model.Covid19StatsEntity
import org.healthapi.android.boilerplate.data.repository.Covid19StatsRemote
import org.healthapi.android.boilerplate.remote.mapper.Covid19StatsEntityMapper
import javax.inject.Inject

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [Covid19StatsRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class Covid19StatsRemoteImpl @Inject constructor(private val covid19StatsService: Covid19StatsService,
                                                 private val entityMapper: Covid19StatsEntityMapper) :
        Covid19StatsRemote {

    /**
     * Retrieve a list of [Covid19StatsEntity] instances from the [Covid19StatsService].
     */
    override fun getStats(): Single<List<Covid19StatsEntity>> {
        return covid19StatsService.getStatsAll()
                .map {
                    it.map { listItem ->
                        entityMapper.mapFromRemote(listItem)
                    }
                }
    }

}