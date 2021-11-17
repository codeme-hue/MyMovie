package com.fundtastic.mymovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.fundtastic.mymovie.api.ApiUtil
import com.fundtastic.mymovie.api.PeopleService
import com.fundtastic.mymovie.api.TheDiscoverService
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.entity.Movie
import com.fundtastic.mymovie.models.entity.Person
import com.fundtastic.mymovie.models.entity.Tv
import com.fundtastic.mymovie.models.network.DiscoverMovieResponse
import com.fundtastic.mymovie.repository.DiscoverRepository
import com.fundtastic.mymovie.repository.PeopleRepository
import com.fundtastic.mymovie.room.MovieDao
import com.fundtastic.mymovie.room.PeopleDao
import com.fundtastic.mymovie.room.TvDao
import com.fundtastic.mymovie.utils.MockTestUtil.Companion.mockMovie
import com.fundtastic.mymovie.utils.MockTestUtil.Companion.mockPerson
import com.fundtastic.mymovie.utils.MockTestUtil.Companion.mockTv
import com.fundtastic.mymovie.view.ui.main.MainActivityViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityViewModelTest {

  private lateinit var viewModel: MainActivityViewModel

  private lateinit var discoverRepository: DiscoverRepository
  private lateinit var peopleRepository: PeopleRepository

  private val movieDao = mock<MovieDao>()
  private val tvDao = mock<TvDao>()
  private val peopleDao = mock<PeopleDao>()

  private val discoverService = mock<TheDiscoverService>()
  private val peopleService = mock<PeopleService>()

  @Rule
  @JvmField
  val instantExecutorRule = InstantTaskExecutorRule()

  @Before
  fun init() {
    discoverRepository = DiscoverRepository(discoverService, movieDao, tvDao)
    peopleRepository = PeopleRepository(peopleService, peopleDao)
    viewModel = MainActivityViewModel(discoverRepository, peopleRepository)
  }

  @Test
  fun loadMovieList() {
    val loadFromDB = MutableLiveData<List<Movie>>()
    whenever(movieDao.getMovieList(1)).thenReturn(loadFromDB)

    val mockResponse = DiscoverMovieResponse(1, emptyList(), 100, 10)
    val call = ApiUtil.successCall(mockResponse)
    whenever(discoverService.fetchDiscoverMovie(1)).thenReturn(call)

    val data = viewModel.movieListLiveData
    val observer = mock<Observer<Resource<List<Movie>>>>()
    data.observeForever(observer)

    viewModel.postMoviePage(1)
    verify(movieDao).getMovieList(1)
    verifyNoMoreInteractions(discoverService)

    val mockMovieList = ArrayList<Movie>()
    mockMovieList.add(mockMovie())
    loadFromDB.postValue(mockMovieList)
    verify(observer).onChanged(
      Resource.success(viewModel.getMovieListValues()!!.data)
    )
  }

  @Test
  fun loadTvList() {
    val loadFromDB = MutableLiveData<List<Tv>>()
    whenever(tvDao.getTvList(1)).thenReturn(loadFromDB)

    val data = viewModel.tvListLiveData
    val observer = mock<Observer<Resource<List<Tv>>>>()
    data.observeForever(observer)

    viewModel.postTvPage(1)
    verify(tvDao).getTvList(1)
    verifyNoMoreInteractions(discoverService)

    val mockTvList = ArrayList<Tv>()
    mockTvList.add(mockTv())
    loadFromDB.postValue(mockTvList)
    verify(observer).onChanged(
      Resource.success(viewModel.getTvListValues()!!.data)
    )
  }

  @Test
  fun loadPeople() {
    val loadFromDB = MutableLiveData<List<Person>>()
    whenever(peopleDao.getPeople(1)).thenReturn(loadFromDB)

    val data = viewModel.peopleLiveData
    val observer = mock<Observer<Resource<List<Person>>>>()
    data.observeForever(observer)

    viewModel.postPeoplePage(1)
    verify(peopleDao).getPeople(1)
    verifyNoMoreInteractions(peopleService)

    val mockPeople = ArrayList<Person>()
    mockPeople.add(mockPerson())
    loadFromDB.postValue(mockPeople)
    verify(observer).onChanged(
      Resource.success(viewModel.getPeopleValues()!!.data)
    )
  }
}
