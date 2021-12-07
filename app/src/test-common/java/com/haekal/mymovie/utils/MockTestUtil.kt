package com.haekal.mymovie.utils

import com.haekal.mymovie.models.Keyword
import com.haekal.mymovie.models.Review
import com.haekal.mymovie.models.Video
import com.haekal.mymovie.models.entity.Movie
import com.haekal.mymovie.models.entity.Person
import com.haekal.mymovie.models.entity.Tv
import com.haekal.mymovie.models.network.PersonDetail

class MockTestUtil {
  companion object {
    fun mockMovie() = Movie(1, 0, emptyList(), emptyList(), emptyList(), "", false, "", "",
      ArrayList(), "", "", "", "", 0f, 0, false, 0f)

    fun mockTv() = Tv(1, 0, emptyList(), emptyList(), emptyList(), "", 0f, "", 0f, "", "",
      ArrayList(), ArrayList(), "", 1, "", "")

    fun mockPerson() = Person(1, mockPersonDetail(), 0, "", false, "", 0f)
    fun mockMovieList(): List<Movie> {
      val movies = ArrayList<Movie>()
      movies.add(mockMovie())
      movies.add(mockMovie())
      movies.add(mockMovie())
      return movies
    }

    fun mockTvList(): List<Tv> {
      val tvs = ArrayList<Tv>()
      tvs.add(mockTv())
      tvs.add(mockTv())
      tvs.add(mockTv())
      return tvs
    }

    fun mockPersonList(): List<Person> {
      val people = ArrayList<Person>()
      people.add(mockPerson())
      people.add(mockPerson())
      people.add(mockPerson())
      return people
    }

    fun mockKeywordList(): List<Keyword> {
      val keywords = ArrayList<Keyword>()
      keywords.add(Keyword(100, "keyword0"))
      keywords.add(Keyword(101, "keyword1"))
      keywords.add(Keyword(102, "keyword2"))
      return keywords
    }

    fun mockVideoList(): List<Video> {
      val videos = ArrayList<Video>()
      videos.add(Video("123", "video0", "", "", 0, ""))
      videos.add(Video("123", "video0", "", "", 0, ""))
      return videos
    }

    fun mockReviewList(): List<Review> {
      val reviews = ArrayList<Review>()
      reviews.add(Review("123", "", "", ""))
      reviews.add(Review("123", "", "", ""))
      return reviews
    }

    fun mockPersonDetail(): PersonDetail {
      return PersonDetail("", "", "", emptyList(), "")
    }
  }
}
