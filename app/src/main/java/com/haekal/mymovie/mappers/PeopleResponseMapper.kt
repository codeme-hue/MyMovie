package com.haekal.mymovie.mappers

import com.haekal.mymovie.models.network.PeopleResponse
import timber.log.Timber

class PeopleResponseMapper : NetworkResponseMapper<PeopleResponse> {
  override fun onLastPage(response: PeopleResponse): Boolean {
    Timber.d("loadPage : ${response.page}/${response.total_pages}")
    return response.page > response.total_pages
  }
}
