package org.healthapi.android.boilerplate.ui.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.healthapi.android.boilerplate.ui.browse.BrowseActivity
import org.healthapi.android.boilerplate.ui.injection.scopes.PerActivity

@Module
abstract class ActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [BrowseActivityModule::class])
    abstract fun bindMainActivity(): BrowseActivity

}