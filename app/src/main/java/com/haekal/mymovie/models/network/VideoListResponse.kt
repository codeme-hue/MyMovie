package com.haekal.mymovie.models.network

import com.haekal.mymovie.models.NetworkResponseModel
import com.haekal.mymovie.models.Video

data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel
