package com.fundtastic.mymovie.models.network

import com.fundtastic.mymovie.models.NetworkResponseModel
import com.fundtastic.mymovie.models.Review

class ReviewListResponse(
  val id: Int,
  val page: Int,
  val results: List<Review>,
  val total_pages: Int,
  val total_results: Int
) : NetworkResponseModel
