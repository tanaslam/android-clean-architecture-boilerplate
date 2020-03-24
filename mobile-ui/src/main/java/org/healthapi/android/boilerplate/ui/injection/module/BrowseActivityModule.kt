package org.healthapi.android.boilerplate.ui.injection.module

import dagger.Module
import dagger.Provides
import org.healthapi.android.boilerplate.domain.interactor.browse.GetCovid19Stats
import org.healthapi.android.boilerplate.presentation.browse.BrowseCovid19StatsMvp
import org.healthapi.android.boilerplate.presentation.browse.BrowseCovid19StatsPresenter
import org.healthapi.android.boilerplate.presentation.mapper.Covid19StatsViewMapper
import org.healthapi.android.boilerplate.ui.browse.BrowseActivity
import org.healthapi.android.boilerplate.ui.injection.scopes.PerActivity



/**
 * Module used to provide dependencies at an activity-level.
 */
@Module
open class BrowseActivityModule {

    @PerActivity
    @Provides
    internal fun provideBrowseView(browseActivity: BrowseActivity): BrowseCovid19StatsMvp.View {
        return browseActivity
    }

    @PerActivity
    @Provides
    internal fun provideBrowsePresenter(mainView: BrowseCovid19StatsMvp.View,
                                        getCovid19Stats: GetCovid19Stats, mapper: Covid19StatsViewMapper):
            BrowseCovid19StatsMvp.Presenter {
        return BrowseCovid19StatsPresenter(mainView, getCovid19Stats, mapper)
    }

}
