package org.buffer.android.boilerplate.ui.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import org.buffer.android.boilerplate.ui.BufferooApplication
import org.buffer.android.boilerplate.ui.injection.module.ActivityModule
import org.buffer.android.boilerplate.ui.injection.module.ApplicationModule
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication

@PerApplication
@Component(modules = [
    ApplicationModule::class,
    ActivityModule::class,
    AndroidSupportInjectionModule::class
])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    override fun inject(app: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

}
