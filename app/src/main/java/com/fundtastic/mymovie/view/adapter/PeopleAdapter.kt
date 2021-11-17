package com.fundtastic.mymovie.view.adapter

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.fundtastic.mymovie.R
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.entity.Person
import com.fundtastic.mymovie.view.viewholder.PeopleViewHolder
import com.skydoves.whatif.whatIfNotNull

class PeopleAdapter : BaseAdapter() {

  init {
    addSection(ArrayList<Person>())
  }

  fun addPeople(resource: Resource<List<Person>>) {
    resource.data.whatIfNotNull {
      sections()[0].addAll(it)
      notifyDataSetChanged()
    }
  }

  override fun layout(sectionRow: SectionRow) = R.layout.item_person

  override fun viewHolder(layout: Int, view: View) = PeopleViewHolder(view)
}
