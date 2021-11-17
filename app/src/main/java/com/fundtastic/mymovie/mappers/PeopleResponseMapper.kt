package com.fundtastic.mymovie.mappers

import com.fundtastic.mymovie.models.network.PeopleResponse
import timber.log.Timber

class PeopleResponseMapper : NetworkResponseMapper<PeopleResponse> {
  override fun onLastPage(response: PeopleResponse): Boolean {
    Timber.d("loadPage : ${response.page}/${response.total_pages}")
    return response.page > response.total_pages
  }
}
