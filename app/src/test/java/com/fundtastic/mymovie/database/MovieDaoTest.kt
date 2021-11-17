package com.fundtastic.mymovie.database

import com.fundtastic.mymovie.models.entity.Movie
import com.fundtastic.mymovie.utils.LiveDataTestUtil
import com.fundtastic.mymovie.utils.MockTestUtil.Companion.mockMovie
import com.fundtastic.mymovie.utils.MockTestUtil.Companion.mockMovieList
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class MovieDaoTest : LocalDatabase() {

  @Test
  fun insertAndReadTest() {
    db.movieDao().insertMovieList(mockMovieList())
    val loadFromDB = LiveDataTestUtil.getValue(db.movieDao().getMovieList(1))[0]
    assertThat(loadFromDB.page, `is`(1))
    assertThat(loadFromDB.id, `is`(123))
  }

  @Test
  fun updateAndReadTest() {
    val movieList = ArrayList<Movie>()
    val movie = mockMovie()
    movieList.add(movie)
    db.movieDao().insertMovieList(movieList)

    val loadFromDB = db.movieDao().getMovie(movie.id)
    assertThat(loadFromDB.page, `is`(1))

    movie.page = 10
    db.movieDao().updateMovie(movie)

    val updated = db.movieDao().getMovie(movie.id)
    assertThat(updated.page, `is`(10))
  }
}
