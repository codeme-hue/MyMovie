package com.haekal.mymovie.mappers

import com.haekal.mymovie.models.network.VideoListResponse

class VideoResponseMapper : NetworkResponseMapper<VideoListResponse> {
  override fun onLastPage(response: VideoListResponse): Boolean {
    return true
  }
}
