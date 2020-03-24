package org.healthapi.android.boilerplate.presentation.browse

import io.reactivex.observers.DisposableSingleObserver
import org.healthapi.android.boilerplate.domain.interactor.SingleUseCase
import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import org.healthapi.android.boilerplate.presentation.mapper.Covid19StatsViewMapper
import javax.inject.Inject

class BrowseCovid19StatsPresenter @Inject constructor(val browseView: BrowseCovid19StatsMvp.View,
                                                      private val useCase: SingleUseCase<List<Covid19Stats>, Void>,
                                                      val mapper: Covid19StatsViewMapper):
        BrowseCovid19StatsMvp.Presenter {

    init {
        browseView.setPresenter(this)
    }

    override fun start() {
        retrieveStats()
    }

    override fun stop() {
        useCase.dispose()
    }

    override fun retrieveStats() {
        useCase.execute(Covid19StatsSubscriber())
    }

    internal fun handleSuccess(covid19Stats: List<Covid19Stats>) {
        browseView.hideErrorState()
        if (covid19Stats.isNotEmpty()) {
            browseView.hideEmptyState()
            browseView.showStats(covid19Stats.map { mapper.mapToView(it) })
        } else {
            browseView.hideStats()
            browseView.showEmptyState()
        }
    }

    inner class Covid19StatsSubscriber: DisposableSingleObserver<List<Covid19Stats>>() {

        override fun onSuccess(t: List<Covid19Stats>) {
            handleSuccess(t)
        }

        override fun onError(exception: Throwable) {
            browseView.hideStats()
            browseView.hideEmptyState()
            browseView.showErrorState()
        }

    }

}