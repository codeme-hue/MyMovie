package com.fundtastic.mymovie.repository

import androidx.lifecycle.LiveData
import com.fundtastic.mymovie.api.ApiResponse
import com.fundtastic.mymovie.api.TheDiscoverService
import com.fundtastic.mymovie.mappers.MovieResponseMapper
import com.fundtastic.mymovie.mappers.TvResponseMapper
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.entity.Movie
import com.fundtastic.mymovie.models.entity.Tv
import com.fundtastic.mymovie.models.network.DiscoverMovieResponse
import com.fundtastic.mymovie.models.network.DiscoverTvResponse
import com.fundtastic.mymovie.room.MovieDao
import com.fundtastic.mymovie.room.TvDao
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
class DiscoverRepository @Inject constructor(
  val discoverService: TheDiscoverService,
  val movieDao: MovieDao,
  val tvDao: TvDao
) : Repository {

  init {
    Timber.d("Injection DiscoverRepository")
  }

  fun loadMovies(page: Int): LiveData<Resource<List<Movie>>> {
    return object : NetworkBoundRepository<List<Movie>, DiscoverMovieResponse, MovieResponseMapper>() {
      override fun saveFetchData(items: DiscoverMovieResponse) {
        for (item in items.results) {
          item.page = page
        }
        movieDao.insertMovieList(movies = items.results)
      }

      override fun shouldFetch(data: List<Movie>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Movie>> {
        return movieDao.getMovieList(page_ = page)
      }

      override fun fetchService(): LiveData<ApiResponse<DiscoverMovieResponse>> {
        return discoverService.fetchDiscoverMovie(page = page)
      }

      override fun mapper(): MovieResponseMapper {
        return MovieResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("onFetchFailed $message")
      }
    }.asLiveData()
  }

  fun loadTvs(page: Int): LiveData<Resource<List<Tv>>> {
    return object : NetworkBoundRepository<List<Tv>, DiscoverTvResponse, TvResponseMapper>() {
      override fun saveFetchData(items: DiscoverTvResponse) {
        for (item in items.results) {
          item.page = page
        }
        tvDao.insertTv(tvs = items.results)
      }

      override fun shouldFetch(data: List<Tv>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Tv>> {
        return tvDao.getTvList(page_ = page)
      }

      override fun fetchService(): LiveData<ApiResponse<DiscoverTvResponse>> {
        return discoverService.fetchDiscoverTv(page = page)
      }

      override fun mapper(): TvResponseMapper {
        return TvResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("oFetchFailed $message")
      }
    }.asLiveData()
  }
}
