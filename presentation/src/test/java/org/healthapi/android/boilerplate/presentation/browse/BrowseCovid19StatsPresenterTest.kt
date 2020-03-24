package org.healthapi.android.boilerplate.presentation.browse

import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.observers.DisposableSingleObserver
import org.healthapi.android.boilerplate.domain.interactor.browse.GetCovid19Stats
import org.healthapi.android.boilerplate.domain.model.Covid19Stats
import org.healthapi.android.boilerplate.presentation.mapper.Covid19StatsViewMapper
import org.healthapi.android.boilerplate.presentation.test.factory.BufferooFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BrowseCovid19StatsPresenterTest {

    private lateinit var mockBrowseBufferoosView: BrowseCovid19StatsMvp.View
    private lateinit var mockCovid19StatsMapper: Covid19StatsViewMapper
    private lateinit var mockGetCovid19Stats: GetCovid19Stats

    private lateinit var browseCovid19StatsPresenter: BrowseCovid19StatsPresenter
    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<Covid19Stats>>>

    @Before
    fun setup() {
        captor = argumentCaptor<DisposableSingleObserver<List<Covid19Stats>>>()
        mockBrowseBufferoosView = mock()
        mockCovid19StatsMapper = mock()
        mockGetCovid19Stats = mock()
        browseCovid19StatsPresenter = BrowseCovid19StatsPresenter(mockBrowseBufferoosView,
                mockGetCovid19Stats, mockCovid19StatsMapper)
    }

    //<editor-fold desc="Retrieve Bufferoos">
    @Test
    fun retrieveBufferoosHidesErrorState() {
        browseCovid19StatsPresenter.retrieveStats()

        verify(mockGetCovid19Stats).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(BufferooFactory.makeBufferooList(2))
        verify(mockBrowseBufferoosView).hideErrorState()
    }

    @Test
    fun retrieveBufferoosHidesEmptyState() {
        browseCovid19StatsPresenter.retrieveStats()

        verify(mockGetCovid19Stats).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(BufferooFactory.makeBufferooList(2))
        verify(mockBrowseBufferoosView).hideEmptyState()
    }

    @Test
    fun retrieveBufferoosShowsBufferoos() {
        val bufferoos = BufferooFactory.makeBufferooList(2)
        browseCovid19StatsPresenter.retrieveStats()

        verify(mockGetCovid19Stats).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(bufferoos)
        verify(mockBrowseBufferoosView).showStats(
                bufferoos.map { mockCovid19StatsMapper.mapToView(it) })
    }

    @Test
    fun retrieveBufferoosShowsEmptyState() {
        browseCovid19StatsPresenter.retrieveStats()

        verify(mockGetCovid19Stats).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(BufferooFactory.makeBufferooList(0))
        verify(mockBrowseBufferoosView).showEmptyState()
    }

    @Test
    fun retrieveBufferoosHidesBufferoos() {
        browseCovid19StatsPresenter.retrieveStats()

        verify(mockGetCovid19Stats).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(BufferooFactory.makeBufferooList(0))
        verify(mockBrowseBufferoosView).hideStats()
    }

    @Test
    fun retrieveBufferoosShowsErrorState() {
        browseCovid19StatsPresenter.retrieveStats()

        verify(mockGetCovid19Stats).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockBrowseBufferoosView).showErrorState()
    }

    @Test
    fun retrieveBufferoosHidesBufferoosWhenErrorThrown() {
        browseCovid19StatsPresenter.retrieveStats()

        verify(mockGetCovid19Stats).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockBrowseBufferoosView).hideStats()
    }

    @Test
    fun retrieveBufferoosHidesEmptyStateWhenErrorThrown() {
        browseCovid19StatsPresenter.retrieveStats()

        verify(mockGetCovid19Stats).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockBrowseBufferoosView).hideEmptyState()
    }

}