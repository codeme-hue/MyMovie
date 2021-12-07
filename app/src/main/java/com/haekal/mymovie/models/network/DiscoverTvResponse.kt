package com.haekal.mymovie.models.network

import com.haekal.mymovie.models.NetworkResponseModel
import com.haekal.mymovie.models.entity.Tv

data class DiscoverTvResponse(
  val page: Int,
  val results: List<Tv>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
