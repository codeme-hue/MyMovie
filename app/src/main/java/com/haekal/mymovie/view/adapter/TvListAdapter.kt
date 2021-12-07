package com.haekal.mymovie.view.adapter

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.haekal.mymovie.R
import com.haekal.mymovie.models.Resource
import com.haekal.mymovie.models.entity.Tv
import com.haekal.mymovie.view.viewholder.TvListViewHolder
import com.skydoves.whatif.whatIfNotNull

class TvListAdapter : BaseAdapter() {

  init {
    addSection(ArrayList<Tv>())
  }

  fun addTvList(resource: Resource<List<Tv>>) {
    resource.data.whatIfNotNull {
      sections()[0].addAll(it)
      notifyDataSetChanged()
    }
  }

  override fun layout(sectionRow: SectionRow) = R.layout.item_poster

  override fun viewHolder(layout: Int, view: View) = TvListViewHolder(view)
}
