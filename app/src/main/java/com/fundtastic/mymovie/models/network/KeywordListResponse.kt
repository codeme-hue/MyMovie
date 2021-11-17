package com.fundtastic.mymovie.models.network

import com.fundtastic.mymovie.models.Keyword
import com.fundtastic.mymovie.models.NetworkResponseModel

data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel
