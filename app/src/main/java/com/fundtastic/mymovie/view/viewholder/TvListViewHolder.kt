package com.fundtastic.mymovie.view.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.fundtastic.mymovie.api.Api
import com.fundtastic.mymovie.models.entity.Tv
import com.fundtastic.mymovie.view.ui.details.tv.TvDetailActivity
import kotlinx.android.synthetic.main.item_poster.view.item_poster_palette
import kotlinx.android.synthetic.main.item_poster.view.item_poster_post
import kotlinx.android.synthetic.main.item_poster.view.item_poster_title

class TvListViewHolder constructor(
  val view: View
) : BaseViewHolder(view) {

  private lateinit var tv: Tv

  @Throws(Exception::class)
  override fun bindData(data: Any) {
    if (data is Tv) {
      tv = data
      drawItem()
    }
  }

  private fun drawItem() {
    itemView.run {
      item_poster_title.text = tv.name
      tv.poster_path.let {
        Glide.with(context)
          .load(Api.getPosterPath(it!!))
          .listener(GlidePalette.with(Api.getPosterPath(it))
            .use(BitmapPalette.Profile.VIBRANT)
            .intoBackground(item_poster_palette)
            .crossfade(true))
          .into(item_poster_post)
      }
    }
  }

  override fun onClick(v: View?) = TvDetailActivity.startActivityModel(context(), tv)

  override fun onLongClick(p0: View?) = false
}
