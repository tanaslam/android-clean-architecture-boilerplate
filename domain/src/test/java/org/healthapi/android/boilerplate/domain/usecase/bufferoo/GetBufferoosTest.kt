package org.healthapi.android.boilerplate.domain.usecase.bufferoo

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.healthapi.android.boilerplate.domain.executor.PostExecutionThread
import org.healthapi.android.boilerplate.domain.executor.ThreadExecutor
import org.healthapi.android.boilerplate.domain.interactor.browse.GetBufferoos
import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import org.healthapi.android.boilerplate.domain.repository.Covid19StatsRepository
import org.healthapi.android.boilerplate.domain.test.factory.BufferooFactory
import org.junit.Before
import org.junit.Test

class GetBufferoosTest {

    private lateinit var getBufferoos: GetBufferoos

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockCovid19StatsRepository: Covid19StatsRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockCovid19StatsRepository = mock()
        getBufferoos = GetBufferoos(mockCovid19StatsRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getBufferoos.buildUseCaseObservable(null)
        verify(mockCovid19StatsRepository).getStats()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubBufferooRepositoryGetBufferoos(Single.just(BufferooFactory.makeBufferooList(2)))
        val testObserver = getBufferoos.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val bufferoos = BufferooFactory.makeBufferooList(2)
        stubBufferooRepositoryGetBufferoos(Single.just(bufferoos))
        val testObserver = getBufferoos.buildUseCaseObservable(null).test()
        testObserver.assertValue(bufferoos)
    }

    private fun stubBufferooRepositoryGetBufferoos(single: Single<List<Covid19Stats>>) {
        whenever(mockCovid19StatsRepository.getStats())
                .thenReturn(single)
    }

}