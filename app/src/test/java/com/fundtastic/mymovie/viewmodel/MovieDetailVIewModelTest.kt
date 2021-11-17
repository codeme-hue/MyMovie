package com.fundtastic.mymovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.fundtastic.mymovie.api.ApiUtil.successCall
import com.fundtastic.mymovie.api.MovieService
import com.fundtastic.mymovie.models.Keyword
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.Review
import com.fundtastic.mymovie.models.Video
import com.fundtastic.mymovie.models.network.KeywordListResponse
import com.fundtastic.mymovie.models.network.ReviewListResponse
import com.fundtastic.mymovie.models.network.VideoListResponse
import com.fundtastic.mymovie.repository.MovieRepository
import com.fundtastic.mymovie.room.MovieDao
import com.fundtastic.mymovie.utils.MockTestUtil.Companion.mockKeywordList
import com.fundtastic.mymovie.utils.MockTestUtil.Companion.mockMovie
import com.fundtastic.mymovie.utils.MockTestUtil.Companion.mockReviewList
import com.fundtastic.mymovie.utils.MockTestUtil.Companion.mockVideoList
import com.fundtastic.mymovie.view.ui.details.movie.MovieDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieDetailVIewModelTest {

  private lateinit var viewModel: MovieDetailViewModel

  private lateinit var repository: MovieRepository
  private val movieDao = mock<MovieDao>()

  private val service = mock<MovieService>()

  @Rule
  @JvmField
  val instantExecutorRule = InstantTaskExecutorRule()

  @Before
  fun init() {
    repository = MovieRepository(service, movieDao)
    viewModel = MovieDetailViewModel(repository)
  }

  @Test
  fun loadKeywordList() {
    val loadFromDB = mockMovie()
    whenever(movieDao.getMovie(123)).thenReturn(loadFromDB)

    val mockResponse = KeywordListResponse(123, mockKeywordList())
    val call = successCall(mockResponse)
    whenever(service.fetchKeywords(123)).thenReturn(call)

    val data = repository.loadKeywordList(123)
    val observer = mock<Observer<Resource<List<Keyword>>>>()
    data.observeForever(observer)

    viewModel.postMovieId(123)
    verify(movieDao, times(3)).getMovie(123)
    verify(observer).onChanged(
      Resource.success(mockKeywordList()))

    val updatedMovie = mockMovie()
    updatedMovie.keywords = mockKeywordList()
    verify(movieDao).updateMovie(updatedMovie)
  }

  @Test
  fun loadVideoList() {
    val loadFromDB = mockMovie()
    whenever(movieDao.getMovie(123)).thenReturn(loadFromDB)

    val mockResponse = VideoListResponse(123, mockVideoList())
    val call = successCall(mockResponse)
    whenever(service.fetchVideos(123)).thenReturn(call)

    val data = repository.loadVideoList(123)
    val observer = mock<Observer<Resource<List<Video>>>>()
    data.observeForever(observer)

    viewModel.postMovieId(123)
    verify(movieDao, times(3)).getMovie(123)
    verify(observer).onChanged(
      Resource.success(mockVideoList())
    )

    val updatedMovie = mockMovie()
    updatedMovie.videos = mockVideoList()
    verify(movieDao).updateMovie(updatedMovie)
  }

  @Test
  fun loadReviewList() {
    val loadFromDB = mockMovie()
    whenever(movieDao.getMovie(123)).thenReturn(loadFromDB)

    val mockResponse = ReviewListResponse(123, 1, mockReviewList(), 100, 100)
    val call = successCall(mockResponse)
    whenever(service.fetchReviews(123)).thenReturn(call)

    val data = repository.loadReviewsList(123)
    val observer = mock<Observer<Resource<List<Review>>>>()
    data.observeForever(observer)

    viewModel.postMovieId(123)
    verify(movieDao, times(3)).getMovie(123)
    verify(observer).onChanged(
      Resource.success(mockReviewList())
    )

    val updatedMovie = mockMovie()
    updatedMovie.reviews = mockReviewList()
    verify(movieDao).updateMovie(updatedMovie)
  }
}
