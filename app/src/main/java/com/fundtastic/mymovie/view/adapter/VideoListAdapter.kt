package com.fundtastic.mymovie.view.adapter

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.fundtastic.mymovie.R
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.Video
import com.fundtastic.mymovie.view.viewholder.VideoListViewHolder
import com.skydoves.whatif.whatIfNotNull

class VideoListAdapter : BaseAdapter() {

  init {
    addSection(ArrayList<Video>())
  }

  fun addVideoList(resource: Resource<List<Video>>) {
    resource.data.whatIfNotNull {
      sections()[0].addAll(it)
      notifyDataSetChanged()
    }
  }

  override fun layout(sectionRow: SectionRow) = R.layout.item_video

  override fun viewHolder(layout: Int, view: View) = VideoListViewHolder(view)
}
