package org.healthapi.android.boilerplate.remote

import io.reactivex.Single
import org.healthapi.android.boilerplate.remote.model.Covid19StatsModel
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface Covid19StatsService {

    @GET("countries")
    fun getStatsAll(): Single<List<Covid19StatsModel>>

    class Response {
        lateinit var stat: Covid19StatsModel
    }

}
