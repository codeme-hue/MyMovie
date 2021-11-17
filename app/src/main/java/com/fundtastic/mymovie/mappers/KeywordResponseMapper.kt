package com.fundtastic.mymovie.mappers

import com.fundtastic.mymovie.models.network.KeywordListResponse

class KeywordResponseMapper : NetworkResponseMapper<KeywordListResponse> {
  override fun onLastPage(response: KeywordListResponse): Boolean {
    return true
  }
}
