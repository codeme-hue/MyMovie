package com.fundtastic.mymovie.mappers

import com.fundtastic.mymovie.models.network.VideoListResponse

class VideoResponseMapper : NetworkResponseMapper<VideoListResponse> {
  override fun onLastPage(response: VideoListResponse): Boolean {
    return true
  }
}
