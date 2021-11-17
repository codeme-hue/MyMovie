package com.fundtastic.mymovie.view.adapter

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.fundtastic.mymovie.R
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.Review
import com.fundtastic.mymovie.view.viewholder.ReviewListViewHolder
import com.skydoves.whatif.whatIfNotNull

class ReviewListAdapter : BaseAdapter() {

  init {
    addSection(ArrayList<Review>())
  }

  fun addReviewList(resource: Resource<List<Review>>) {
    resource.data.whatIfNotNull {
      sections()[0].addAll(it)
      notifyDataSetChanged()
    }
  }

  override fun layout(sectionRow: SectionRow) = R.layout.item_review

  override fun viewHolder(layout: Int, view: View) = ReviewListViewHolder(view)
}
