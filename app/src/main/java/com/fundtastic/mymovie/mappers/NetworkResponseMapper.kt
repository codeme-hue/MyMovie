package com.fundtastic.mymovie.mappers

import com.fundtastic.mymovie.models.NetworkResponseModel

interface NetworkResponseMapper<in FROM : NetworkResponseModel> {
  fun onLastPage(response: FROM): Boolean
}
