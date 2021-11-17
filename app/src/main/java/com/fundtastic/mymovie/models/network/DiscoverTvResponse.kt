package com.fundtastic.mymovie.models.network

import com.fundtastic.mymovie.models.NetworkResponseModel
import com.fundtastic.mymovie.models.entity.Tv

data class DiscoverTvResponse(
  val page: Int,
  val results: List<Tv>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
