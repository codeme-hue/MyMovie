package com.fundtastic.mymovie.view.ui.details.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.fundtastic.mymovie.models.Keyword
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.Review
import com.fundtastic.mymovie.models.Video
import com.fundtastic.mymovie.repository.TvRepository
import com.fundtastic.mymovie.utils.AbsentLiveData
import javax.inject.Inject
import timber.log.Timber

class TvDetailViewModel @Inject constructor(
  private val repository: TvRepository
) : ViewModel() {

  private val tvIdLiveData: MutableLiveData<Int> = MutableLiveData()
  val keywordListLiveData: LiveData<Resource<List<Keyword>>>
  val videoListLiveData: LiveData<Resource<List<Video>>>
  val reviewListLiveData: LiveData<Resource<List<Review>>>

  init {
    Timber.d("Injection TvDetailViewModel")

    this.keywordListLiveData = tvIdLiveData.switchMap {
      tvIdLiveData.value?.let {
        repository.loadKeywordList(it)
      } ?: AbsentLiveData.create()
    }

    this.videoListLiveData = tvIdLiveData.switchMap {
      tvIdLiveData.value?.let {
        repository.loadVideoList(it)
      } ?: AbsentLiveData.create()
    }

    this.reviewListLiveData = tvIdLiveData.switchMap {
      tvIdLiveData.value?.let {
        repository.loadReviewsList(it)
      } ?: AbsentLiveData.create()
    }
  }

  fun postTvId(id: Int) = tvIdLiveData.postValue(id)
}
