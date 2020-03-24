package org.healthapi.android.boilerplate.ui

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import org.healthapi.android.boilerplate.ui.injection.component.DaggerApplicationComponent
import timber.log.Timber

class Covid19StatsApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
