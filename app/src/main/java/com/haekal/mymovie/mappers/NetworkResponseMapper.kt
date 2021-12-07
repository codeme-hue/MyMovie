package com.haekal.mymovie.mappers

import com.haekal.mymovie.models.NetworkResponseModel

interface NetworkResponseMapper<in FROM : NetworkResponseModel> {
  fun onLastPage(response: FROM): Boolean
}
