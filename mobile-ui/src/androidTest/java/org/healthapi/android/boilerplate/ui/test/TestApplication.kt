package org.healthapi.android.boilerplate.ui.test

import androidx.test.platform.app.InstrumentationRegistry
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import org.healthapi.android.boilerplate.ui.injection.component.DaggerTestApplicationComponent
import org.healthapi.android.boilerplate.ui.injection.component.TestApplicationComponent

class TestApplication : DaggerApplication() {

    private lateinit var appComponent: TestApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerTestApplicationComponent.builder().application(this).build()
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {

        fun appComponent(): TestApplicationComponent {
            return (InstrumentationRegistry.getTargetContext().applicationContext as TestApplication).appComponent
        }

    }

}