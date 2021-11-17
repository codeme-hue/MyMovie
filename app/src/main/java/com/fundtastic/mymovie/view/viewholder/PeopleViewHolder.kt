package com.fundtastic.mymovie.view.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.fundtastic.mymovie.api.Api
import com.fundtastic.mymovie.models.entity.Person
import com.fundtastic.mymovie.view.ui.details.person.PersonDetailActivity
import kotlinx.android.synthetic.main.item_person.view.item_person_name
import kotlinx.android.synthetic.main.item_person.view.item_person_profile

class PeopleViewHolder constructor(
  val view: View
) : BaseViewHolder(view) {

  private lateinit var person: Person

  @Throws(Exception::class)
  override fun bindData(data: Any) {
    if (data is Person) {
      person = data
      drawItem()
    }
  }

  private fun drawItem() {
    itemView.run {
      item_person_name.text = person.name
      person.profile_path?.let {
        Glide.with(context)
          .load(Api.getPosterPath(it))
          .apply(RequestOptions().circleCrop())
          .into(item_person_profile)
      }
    }
  }

  override fun onClick(p0: View?) =
    PersonDetailActivity.startActivity(context(), person, itemView.item_person_profile)

  override fun onLongClick(p0: View?) = false
}
