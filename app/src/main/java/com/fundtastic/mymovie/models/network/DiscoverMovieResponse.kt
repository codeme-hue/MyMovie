package com.fundtastic.mymovie.models.network

import com.fundtastic.mymovie.models.NetworkResponseModel
import com.fundtastic.mymovie.models.entity.Movie

data class DiscoverMovieResponse(
  val page: Int,
  val results: List<Movie>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
