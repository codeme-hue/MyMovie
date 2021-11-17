package com.fundtastic.mymovie.models.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fundtastic.mymovie.models.Keyword
import com.fundtastic.mymovie.models.Review
import com.fundtastic.mymovie.models.Video
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Movie(
  @PrimaryKey val id: Int,
  var page: Int,
  var keywords: List<Keyword>? = ArrayList(),
  var videos: List<Video>? = ArrayList(),
  var reviews: List<Review>? = ArrayList(),
  val poster_path: String?,
  val adult: Boolean,
  val overview: String,
  val release_date: String,
  val genre_ids: List<Int>,
  val original_title: String,
  val original_language: String,
  val title: String,
  val backdrop_path: String?,
  val popularity: Float,
  val vote_count: Int,
  val video: Boolean,
  val vote_average: Float
) : Parcelable
