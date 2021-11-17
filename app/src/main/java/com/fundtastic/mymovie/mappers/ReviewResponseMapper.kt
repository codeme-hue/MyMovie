package com.fundtastic.mymovie.mappers

import com.fundtastic.mymovie.models.network.ReviewListResponse

class ReviewResponseMapper : NetworkResponseMapper<ReviewListResponse> {
  override fun onLastPage(response: ReviewListResponse): Boolean {
    return true
  }
}
