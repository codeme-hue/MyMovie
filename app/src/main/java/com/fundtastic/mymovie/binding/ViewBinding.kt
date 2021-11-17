package com.fundtastic.mymovie.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.ChipGroup
import com.fundtastic.mymovie.api.Api
import com.fundtastic.mymovie.extension.addPrimaryChip
import com.fundtastic.mymovie.extension.bindResource
import com.fundtastic.mymovie.extension.requestGlideListener
import com.fundtastic.mymovie.extension.visible
import com.fundtastic.mymovie.models.Keyword
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.entity.Movie
import com.fundtastic.mymovie.models.entity.Person
import com.fundtastic.mymovie.models.entity.Tv
import com.fundtastic.mymovie.models.network.PersonDetail
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullOrEmpty

@BindingAdapter("visibilityByResource")
fun bindVisibilityByResource(view: View, resource: Resource<List<Any>>?) {
  view.bindResource(resource) {
    it.data.whatIfNotNull {
      view.visible()
    }
  }
}

@BindingAdapter("mapKeywordList")
fun bindMapKeywordList(view: ChipGroup, resource: Resource<List<Keyword>>?) {
  view.bindResource(resource) {
    it.data.whatIfNotNullOrEmpty { keywords ->
      view.visible()
      keywords.forEach { keyword -> view.addPrimaryChip(keyword.name) }
    }
  }
}

@BindingAdapter("biography")
fun bindBiography(view: TextView, resource: Resource<PersonDetail>?) {
  view.bindResource(resource) {
    view.text = it.data?.biography
  }
}

@BindingAdapter("nameTags")
fun bindTags(view: ChipGroup, resource: Resource<PersonDetail>?) {
  view.bindResource(resource) {
    it.data?.also_known_as.whatIfNotNullOrEmpty { knows ->
      knows.forEach { know -> view.addPrimaryChip(know) }
      view.visible()
    }
  }
}

@BindingAdapter("bindReleaseDate")
fun bindReleaseDate(view: TextView, movie: Movie) {
  view.text = "Release Date : ${movie.release_date}"
}

@BindingAdapter("bindAirDate")
fun bindAirDate(view: TextView, tv: Tv) {
  view.text = "First Air Date : ${tv.first_air_date}"
}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, movie: Movie) {
  if (movie.backdrop_path != null) {
    Glide.with(view.context).load(Api.getBackdropPath(movie.backdrop_path))
      .listener(view.requestGlideListener())
      .into(view)
  } else {
    Glide.with(view.context).load(Api.getBackdropPath(movie.poster_path!!))
      .listener(view.requestGlideListener())
      .into(view)
  }
}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, tv: Tv) {
  if (tv.backdrop_path != null) {
    Glide.with(view.context).load(Api.getBackdropPath(tv.backdrop_path))
      .listener(view.requestGlideListener())
      .into(view)
  } else if (tv.poster_path != null) {
    Glide.with(view.context).load(Api.getBackdropPath(tv.poster_path))
      .listener(view.requestGlideListener())
      .into(view)
  }
}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, person: Person) {
  if (person.profile_path != null) {
    Glide.with(view.context).load(Api.getBackdropPath(person.profile_path))
      .apply(RequestOptions().circleCrop())
      .into(view)
  }
}
