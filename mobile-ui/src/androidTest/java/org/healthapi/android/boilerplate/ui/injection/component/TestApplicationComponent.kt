package org.healthapi.android.boilerplate.ui.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import org.healthapi.android.boilerplate.domain.executor.PostExecutionThread
import org.healthapi.android.boilerplate.domain.repository.Covid19StatsRepository
import org.healthapi.android.boilerplate.ui.injection.module.ActivityModule
import org.healthapi.android.boilerplate.ui.injection.module.TestApplicationModule
import org.healthapi.android.boilerplate.ui.injection.scopes.PerApplication
import org.healthapi.android.boilerplate.ui.test.TestApplication

@Component(modules = [
    TestApplicationModule::class,
    ActivityModule::class,
    AndroidSupportInjectionModule::class
])
@PerApplication
interface TestApplicationComponent : AndroidInjector<TestApplication> {

    fun bufferooRepository(): Covid19StatsRepository

    fun postExecutionThread(): PostExecutionThread

    fun inject(application: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

}