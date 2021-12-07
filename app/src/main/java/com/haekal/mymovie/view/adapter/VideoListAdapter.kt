package com.haekal.mymovie.view.adapter

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.haekal.mymovie.R
import com.haekal.mymovie.models.Resource
import com.haekal.mymovie.models.Video
import com.haekal.mymovie.view.viewholder.VideoListViewHolder
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
