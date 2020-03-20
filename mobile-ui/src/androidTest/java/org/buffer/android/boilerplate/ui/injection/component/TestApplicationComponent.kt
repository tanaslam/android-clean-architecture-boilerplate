package org.buffer.android.boilerplate.ui.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.repository.BufferooRepository
import org.buffer.android.boilerplate.ui.injection.module.ActivityModule
import org.buffer.android.boilerplate.ui.injection.module.TestApplicationModule
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication
import org.buffer.android.boilerplate.ui.test.TestApplication

@Component(modules = [
    TestApplicationModule::class,
    ActivityModule::class,
    AndroidSupportInjectionModule::class
])
@PerApplication
interface TestApplicationComponent : AndroidInjector<TestApplication> {

    fun bufferooRepository(): BufferooRepository

    fun postExecutionThread(): PostExecutionThread

    fun inject(application: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

}