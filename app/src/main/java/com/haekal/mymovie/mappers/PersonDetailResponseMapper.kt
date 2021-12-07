package com.haekal.mymovie.mappers

import com.haekal.mymovie.models.network.PersonDetail

class PersonDetailResponseMapper : NetworkResponseMapper<PersonDetail> {
  override fun onLastPage(response: PersonDetail): Boolean {
    return true
  }
}
