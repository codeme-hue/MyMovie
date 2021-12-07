package com.haekal.mymovie.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.haekal.mymovie.extension.bindResource
import com.haekal.mymovie.extension.visible
import com.haekal.mymovie.models.Resource
import com.haekal.mymovie.models.Review
import com.haekal.mymovie.models.Status
import com.haekal.mymovie.models.Video
import com.haekal.mymovie.models.entity.Movie
import com.haekal.mymovie.models.entity.Person
import com.haekal.mymovie.models.entity.Tv
import com.haekal.mymovie.view.adapter.*
import com.haekal.mymovie.view.ui.main.MainActivityViewModel
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
