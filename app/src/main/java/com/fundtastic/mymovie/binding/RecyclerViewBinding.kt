package com.fundtastic.mymovie.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fundtastic.mymovie.extension.bindResource
import com.fundtastic.mymovie.extension.visible
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.Review
import com.fundtastic.mymovie.models.Status
import com.fundtastic.mymovie.models.Video
import com.fundtastic.mymovie.models.entity.Movie
import com.fundtastic.mymovie.models.entity.Person
import com.fundtastic.mymovie.models.entity.Tv
import com.fundtastic.mymovie.view.adapter.*
import com.fundtastic.mymovie.view.ui.main.MainActivityViewModel
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.whatif.whatIfNotNullOrEmpty

@BindingAdapter("adapter")
fun bindRecyclerViewAdapter(view: RecyclerView, adapter: BaseAdapter) {
  view.adapter = adapter
}

@BindingAdapter("adapterMovieList")
fun bindAdapterMovieList(view: RecyclerView, resource: Resource<List<Movie>>?) {
  view.bindResource(resource) {
    val adapter = view.adapter as? MovieListAdapter
    adapter?.addMovieList(it)
  }
}

@BindingAdapter("moviePagination")
fun bindMoviePagination(view: RecyclerView, viewModel: MainActivityViewModel) {
  RecyclerViewPaginator(
    recyclerView = view,
    isLoading = { viewModel.getTvListValues()?.status == Status.LOADING },
    loadMore = { viewModel.postMoviePage(it) },
    onLast = { false }
  ).run {
    currentPage = 1
  }
}

@BindingAdapter("adapterPersonList")
fun bindAdapterPersonList(view: RecyclerView, resource: Resource<List<Person>>?) {
  view.bindResource(resource) {
    val adapter = view.adapter as? PeopleAdapter
    adapter?.addPeople(it)
  }
}

@BindingAdapter("personPagination")
fun bindPersonPagination(view: RecyclerView, viewModel: MainActivityViewModel) {
  RecyclerViewPaginator(
    recyclerView = view,
    isLoading = { viewModel.getPeopleValues()?.status == Status.LOADING },
    loadMore = { viewModel.postPeoplePage(it) },
    onLast = { false }
  ).run {
    currentPage = 1
  }
}

@BindingAdapter("adapterTvList")
fun bindAdapterTvList(view: RecyclerView, resource: Resource<List<Tv>>?) {
  view.bindResource(resource) {
    val adapter = view.adapter as? TvListAdapter
    adapter?.addTvList(it)
  }
}

@BindingAdapter("tvPagination")
fun bindTvPagination(view: RecyclerView, viewModel: MainActivityViewModel) {
  RecyclerViewPaginator(
    recyclerView = view,
    isLoading = { viewModel.getTvListValues()?.status == Status.LOADING },
    loadMore = { viewModel.postTvPage(it) },
    onLast = { false }
  ).run {
    currentPage = 1
  }
}

@BindingAdapter("adapterVideoList")
fun bindAdapterVideoList(view: RecyclerView, resource: Resource<List<Video>>?) {
  view.bindResource(resource) {
    val adapter = view.adapter as? VideoListAdapter
    adapter?.addVideoList(it)
    it.data.whatIfNotNullOrEmpty {
      view.visible()
    }
  }
}

@BindingAdapter("adapterReviewList")
fun bindAdapterReviewList(view: RecyclerView, resource: Resource<List<Review>>?) {
  view.bindResource(resource) {
    val adapter = view.adapter as? ReviewListAdapter
    adapter?.addReviewList(it)
    it.data.whatIfNotNullOrEmpty {
      view.visible()
      view.setHasFixedSize(true)
    }
  }
}
