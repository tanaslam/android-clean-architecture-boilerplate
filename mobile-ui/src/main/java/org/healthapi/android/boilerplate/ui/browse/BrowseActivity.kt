package org.healthapi.android.boilerplate.ui.browse

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_browse.*
import org.healthapi.android.boilerplate.presentation.browse.BrowseCovid19StatsMvp
import org.healthapi.android.boilerplate.presentation.model.Covid19StatsView
import org.healthapi.android.boilerplate.ui.R
import org.healthapi.android.boilerplate.ui.mapper.Covid19StatsMapper
import javax.inject.Inject

class BrowseActivity : DaggerAppCompatActivity(), BrowseCovid19StatsMvp.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var onboardingPresenter: BrowseCovid19StatsMvp.Presenter

    @Inject
    lateinit var browseAdapter: BrowseAdapter

    @Inject
    lateinit var mapper: Covid19StatsMapper

    override fun setPresenter(presenter: BrowseCovid19StatsMvp.Presenter) {
        onboardingPresenter = presenter
    }

    override fun hideProgress() {
        swiperefresh.isRefreshing = false
        progress.visibility = View.GONE
    }

    override fun showProgress() {
        swiperefresh.isRefreshing = true
        progress.visibility = View.VISIBLE
    }

    override fun showStats(covid19Stats: List<Covid19StatsView>) {
        swiperefresh.isRefreshing = false
        browseAdapter.covid19Stats = covid19Stats.map { mapper.mapToViewModel(it) }
        browseAdapter.notifyDataSetChanged()
        recycler_browse.visibility = View.VISIBLE
    }

    override fun hideStats() {
        recycler_browse.visibility = View.GONE
    }

    override fun showErrorState() {
    }

    override fun hideErrorState() {
    }

    override fun showEmptyState() {
    }

    override fun hideEmptyState() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        AndroidInjection.inject(this)
        setupToolbar()
        setupSwipeToRefreshLayout()
        setupBrowseRecycler()
    }

    private fun setupToolbar() {
        toolbar.title = title
    }

    private fun setupSwipeToRefreshLayout() {
        swiperefresh.setOnRefreshListener(this)
    }

    override fun onStart() {
        super.onStart()
        onboardingPresenter.start()
    }

    private fun setupBrowseRecycler() {
        recycler_browse.layoutManager = LinearLayoutManager(this)
        recycler_browse.adapter = browseAdapter
    }

    override fun onRefresh() {
        onboardingPresenter.retrieveStats()
    }

}