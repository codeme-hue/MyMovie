package com.fundtastic.mymovie.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fundtastic.mymovie.R
import com.fundtastic.mymovie.compose.ViewModelFragment
import com.fundtastic.mymovie.databinding.MainFragmentTvBinding
import com.fundtastic.mymovie.view.adapter.TvListAdapter

class TvListFragment : ViewModelFragment() {

  private val viewModel: MainActivityViewModel by injectActivityVIewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return binding<MainFragmentTvBinding>(inflater, R.layout.main_fragment_tv, container)
      .apply {
        viewModel = this@TvListFragment.viewModel.apply { postTvPage(1) }
        lifecycleOwner = this@TvListFragment
        adapter = TvListAdapter()
      }.root
  }
}
