package com.fundtastic.mymovie.api

import androidx.lifecycle.LiveData
import com.fundtastic.mymovie.models.network.PeopleResponse
import com.fundtastic.mymovie.models.network.PersonDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {

  @GET("/3/person/popular?language=en")
  fun fetchPopularPeople(@Query("page") page: Int): LiveData<ApiResponse<PeopleResponse>>

  @GET("/3/person/{person_id}")
  fun fetchPersonDetail(@Path("person_id") id: Int): LiveData<ApiResponse<PersonDetail>>
}
