package com.haekal.mymovie.models.network

import com.haekal.mymovie.models.NetworkResponseModel
import com.haekal.mymovie.models.entity.Person

data class PeopleResponse(
  val page: Int,
  val results: List<Person>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
