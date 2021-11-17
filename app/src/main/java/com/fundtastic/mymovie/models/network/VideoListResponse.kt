package com.fundtastic.mymovie.models.network

import com.fundtastic.mymovie.models.NetworkResponseModel
import com.fundtastic.mymovie.models.Video

data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel
