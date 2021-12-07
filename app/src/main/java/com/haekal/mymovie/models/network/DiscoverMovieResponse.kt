package com.haekal.mymovie.models.network

import com.haekal.mymovie.models.NetworkResponseModel
import com.haekal.mymovie.models.entity.Movie

data class DiscoverMovieResponse(
  val page: Int,
  val results: List<Movie>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
