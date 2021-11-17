package com.fundtastic.mymovie.models.network

import com.fundtastic.mymovie.models.NetworkResponseModel
import com.fundtastic.mymovie.models.entity.Person

data class PeopleResponse(
  val page: Int,
  val results: List<Person>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
