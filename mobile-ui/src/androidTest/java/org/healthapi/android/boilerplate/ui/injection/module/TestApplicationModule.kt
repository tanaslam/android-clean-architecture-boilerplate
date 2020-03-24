package org.healthapi.android.boilerplate.ui.injection.module

import android.app.Application
import android.content.Context
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import org.healthapi.android.boilerplate.cache.PreferencesHelper
import org.healthapi.android.boilerplate.data.executor.JobExecutor
import org.healthapi.android.boilerplate.data.repository.Covid19StatsCache
import org.healthapi.android.boilerplate.data.repository.Covid19StatsRemote
import org.healthapi.android.boilerplate.domain.executor.PostExecutionThread
import org.healthapi.android.boilerplate.domain.executor.ThreadExecutor
import org.healthapi.android.boilerplate.domain.repository.Covid19StatsRepository
import org.healthapi.android.boilerplate.remote.Covid19StatsService
import org.healthapi.android.boilerplate.ui.UiThread
import org.healthapi.android.boilerplate.ui.injection.scopes.PerApplication

@Module
class TestApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(): PreferencesHelper {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRepository(): Covid19StatsRepository {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideBufferooCache(): Covid19StatsCache {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRemote(): Covid19StatsRemote {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideBufferooService(): Covid19StatsService {
        return mock()
    }

}