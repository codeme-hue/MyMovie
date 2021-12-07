package com.haekal.mymovie.models.network

import com.haekal.mymovie.models.NetworkResponseModel
import com.haekal.mymovie.models.Review

class ReviewListResponse(
  val id: Int,
  val page: Int,
  val results: List<Review>,
  val total_pages: Int,
  val total_results: Int
) : NetworkResponseModel
