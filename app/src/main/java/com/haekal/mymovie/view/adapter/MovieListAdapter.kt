package com.haekal.mymovie.view.adapter

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.haekal.mymovie.R
import com.haekal.mymovie.models.Resource
import com.haekal.mymovie.models.entity.Movie
import com.haekal.mymovie.view.viewholder.MovieListViewHolder
import com.skydoves.whatif.whatIfNotNull

class MovieListAdapter : BaseAdapter() {

  init {
    addSection(ArrayList<Movie>())
  }

  fun addMovieList(resource: Resource<List<Movie>>) {
    resource.data.whatIfNotNull {
      sections()[0].addAll(it)
      notifyDataSetChanged()
    }
  }

  override fun layout(sectionRow: SectionRow) = R.layout.item_poster

  override fun viewHolder(layout: Int, view: View) = MovieListViewHolder(view)
}
