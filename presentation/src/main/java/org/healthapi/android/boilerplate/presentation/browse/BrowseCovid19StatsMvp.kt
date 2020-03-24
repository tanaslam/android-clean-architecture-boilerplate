package org.healthapi.android.boilerplate.presentation.browse

import org.healthapi.android.boilerplate.presentation.BasePresenter
import org.healthapi.android.boilerplate.presentation.BaseView
import org.healthapi.android.boilerplate.presentation.model.Covid19StatsView

/**
 * Defines a contract of operations between the Browse Presenter and Browse View
 */
interface BrowseCovid19StatsMvp {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun showStats(covid19Stats: List<Covid19StatsView>)

        fun hideStats()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {

        fun retrieveStats()

    }

}