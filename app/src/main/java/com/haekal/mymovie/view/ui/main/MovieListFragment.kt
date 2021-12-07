package com.haekal.mymovie.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haekal.mymovie.R
import com.haekal.mymovie.compose.ViewModelFragment
import com.haekal.mymovie.databinding.MainFragmentMovieBinding
import com.haekal.mymovie.view.adapter.MovieListAdapter

class MovieListFragment : ViewModelFragment() {

  private val viewModel: MainActivityViewModel by injectActivityVIewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return binding<MainFragmentMovieBinding>(inflater, R.layout.main_fragment_movie, container)
      .apply {
        viewModel = this@MovieListFragment.viewModel.apply { postMoviePage(1) }
        lifecycleOwner = this@MovieListFragment
        adapter = MovieListAdapter()
      }.root
  }
}
