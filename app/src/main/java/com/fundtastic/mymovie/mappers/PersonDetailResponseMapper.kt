package com.fundtastic.mymovie.mappers

import com.fundtastic.mymovie.models.network.PersonDetail

class PersonDetailResponseMapper : NetworkResponseMapper<PersonDetail> {
  override fun onLastPage(response: PersonDetail): Boolean {
    return true
  }
}
