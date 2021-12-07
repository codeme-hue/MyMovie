package com.haekal.mymovie.models.network

import com.haekal.mymovie.models.Keyword
import com.haekal.mymovie.models.NetworkResponseModel

data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel
