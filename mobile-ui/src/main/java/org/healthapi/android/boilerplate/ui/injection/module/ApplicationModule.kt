package org.healthapi.android.boilerplate.ui.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import org.healthapi.android.boilerplate.cache.Covid19StatsCacheImpl
import org.healthapi.android.boilerplate.cache.PreferencesHelper
import org.healthapi.android.boilerplate.cache.db.DbOpenHelper
import org.healthapi.android.boilerplate.cache.mapper.Covid19StatsEntityMapper
import org.healthapi.android.boilerplate.data.Covid19StatsDataRepository
import org.healthapi.android.boilerplate.data.executor.JobExecutor
import org.healthapi.android.boilerplate.data.mapper.Covid19StatsDataMapper
import org.healthapi.android.boilerplate.data.repository.Covid19StatsCache
import org.healthapi.android.boilerplate.data.repository.Covid19StatsRemote
import org.healthapi.android.boilerplate.data.source.BufferooDataStoreFactory
import org.healthapi.android.boilerplate.domain.executor.PostExecutionThread
import org.healthapi.android.boilerplate.domain.executor.ThreadExecutor
import org.healthapi.android.boilerplate.domain.repository.Covid19StatsRepository
import org.healthapi.android.boilerplate.remote.Covid19StatsRemoteImpl
import org.healthapi.android.boilerplate.remote.Covid19StatsService
import org.healthapi.android.boilerplate.remote.Covid19StatsServiceFactory
import org.healthapi.android.boilerplate.ui.BuildConfig
import org.healthapi.android.boilerplate.ui.UiThread
import org.healthapi.android.boilerplate.ui.injection.scopes.PerApplication

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }


    @Provides
    @PerApplication
    internal fun provideBufferooRepository(factory: BufferooDataStoreFactory,
                                           mapper: Covid19StatsDataMapper): Covid19StatsRepository {
        return Covid19StatsDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideBufferooCache(factory: DbOpenHelper,
                                      entityMapper: Covid19StatsEntityMapper,
                                      mapper: org.healthapi.android.boilerplate.cache.db.mapper.Covid19StatsMapper,
                                      helper: PreferencesHelper): Covid19StatsCache {
        return Covid19StatsCacheImpl(factory, entityMapper, mapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRemote(service: Covid19StatsService,
                                       factory: org.healthapi.android.boilerplate.remote.mapper.Covid19StatsEntityMapper): Covid19StatsRemote {
        return Covid19StatsRemoteImpl(service, factory)
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
        return Covid19StatsServiceFactory.createService(BuildConfig.DEBUG)
    }
}
