package com.haekal.mymovie.mappers

import com.haekal.mymovie.models.network.KeywordListResponse

class KeywordResponseMapper : NetworkResponseMapper<KeywordListResponse> {
  override fun onLastPage(response: KeywordListResponse): Boolean {
    return true
  }
}
