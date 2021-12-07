package com.haekal.mymovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.haekal.mymovie.api.ApiUtil
import com.haekal.mymovie.api.TvService
import com.haekal.mymovie.models.Keyword
import com.haekal.mymovie.models.Resource
import com.haekal.mymovie.models.Review
import com.haekal.mymovie.models.Video
import com.haekal.mymovie.models.network.KeywordListResponse
import com.haekal.mymovie.models.network.ReviewListResponse
import com.haekal.mymovie.models.network.VideoListResponse
import com.haekal.mymovie.repository.TvRepository
import com.haekal.mymovie.room.TvDao
import com.haekal.mymovie.utils.MockTestUtil
import com.haekal.mymovie.utils.MockTestUtil.Companion.mockTv
import com.haekal.mymovie.view.ui.details.tv.TvDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TvDetailViewModelTest {

  private lateinit var viewModel: TvDetailViewModel

  private lateinit var repository: TvRepository
  private val tvDao = mock<TvDao>()

  private val service = mock<TvService>()

  @Rule
  @JvmField
  val instantExecutorRule = InstantTaskExecutorRule()

  @Before
  fun init() {
    repository = TvRepository(service, tvDao)
    viewModel = TvDetailViewModel(repository)
  }

  @Test
  fun loadKeywordList() {
    val loadFromDB = mockTv()
    whenever(tvDao.getTv(123)).thenReturn(loadFromDB)

    val mockResponse = KeywordListResponse(123, MockTestUtil.mockKeywordList())
    val call = ApiUtil.successCall(mockResponse)
    whenever(service.fetchKeywords(123)).thenReturn(call)

    val data = repository.loadKeywordList(123)
    val observer = mock<Observer<Resource<List<Keyword>>>>()
    data.observeForever(observer)

    viewModel.postTvId(123)
    verify(tvDao, times(3)).getTv(123)
    verify(observer).onChanged(
      Resource.success(MockTestUtil.mockKeywordList()))

    val updatedTv = mockTv()
    updatedTv.keywords = MockTestUtil.mockKeywordList()
    verify(tvDao).updateTv(updatedTv)
  }

  @Test
  fun loadVideoList() {
    val loadFromDB = mockTv()
    whenever(tvDao.getTv(123)).thenReturn(loadFromDB)

    val mockResponse = VideoListResponse(123, MockTestUtil.mockVideoList())
    val call = ApiUtil.successCall(mockResponse)
    whenever(service.fetchVideos(123)).thenReturn(call)

    val data = repository.loadVideoList(123)
    val observer = mock<Observer<Resource<List<Video>>>>()
    data.observeForever(observer)

    viewModel.postTvId(123)
    verify(tvDao, times(3)).getTv(123)
    verify(observer).onChanged(
      Resource.success(MockTestUtil.mockVideoList())
    )

    val updatedTv = mockTv()
    updatedTv.videos = MockTestUtil.mockVideoList()
    verify(tvDao).updateTv(updatedTv)
  }

  @Test
  fun loadReviewList() {
    val loadFromDB = mockTv()
    whenever(tvDao.getTv(123)).thenReturn(loadFromDB)

    val mockResponse = ReviewListResponse(123, 1, MockTestUtil.mockReviewList(), 100, 100)
    val call = ApiUtil.successCall(mockResponse)
    whenever(service.fetchReviews(123)).thenReturn(call)

    val data = repository.loadReviewsList(123)
    val observer = mock<Observer<Resource<List<Review>>>>()
    data.observeForever(observer)

    viewModel.postTvId(123)
    verify(tvDao, times(3)).getTv(123)
    verify(observer).onChanged(
      Resource.success(MockTestUtil.mockReviewList())
    )

    val updatedTv = mockTv()
    updatedTv.reviews = MockTestUtil.mockReviewList()
    verify(tvDao).updateTv(updatedTv)
  }
}
