package com.haekal.mymovie.mappers

import com.haekal.mymovie.models.network.ReviewListResponse

class ReviewResponseMapper : NetworkResponseMapper<ReviewListResponse> {
  override fun onLastPage(response: ReviewListResponse): Boolean {
    return true
  }
}
