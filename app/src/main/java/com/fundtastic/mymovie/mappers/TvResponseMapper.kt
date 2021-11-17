package com.fundtastic.mymovie.mappers

import com.fundtastic.mymovie.models.network.DiscoverTvResponse
import timber.log.Timber

class TvResponseMapper : NetworkResponseMapper<DiscoverTvResponse> {
  override fun onLastPage(response: DiscoverTvResponse): Boolean {
    Timber.d("loadPage : ${response.page}/${response.total_pages}")
    return response.page > response.total_pages
  }
}
