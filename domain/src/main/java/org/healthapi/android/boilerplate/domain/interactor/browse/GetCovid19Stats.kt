package org.healthapi.android.boilerplate.domain.interactor.browse

import io.reactivex.Single
import org.healthapi.android.boilerplate.domain.executor.PostExecutionThread
import org.healthapi.android.boilerplate.domain.executor.ThreadExecutor
import org.healthapi.android.boilerplate.domain.interactor.SingleUseCase
import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import org.healthapi.android.boilerplate.domain.repository.Covid19StatsRepository
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [Covid19Stats] instances from the [Covid19StatsRepository]
 */
open class GetCovid19Stats @Inject constructor(private val covid19StatsRepository: Covid19StatsRepository,
                                               threadExecutor: ThreadExecutor,
                                               postExecutionThread: PostExecutionThread) :
        SingleUseCase<List<Covid19Stats>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Single<List<Covid19Stats>> {
        return covid19StatsRepository.getStats()
    }

}