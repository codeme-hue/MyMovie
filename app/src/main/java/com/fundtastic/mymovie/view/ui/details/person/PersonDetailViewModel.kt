package com.fundtastic.mymovie.view.ui.details.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.network.PersonDetail
import com.fundtastic.mymovie.repository.PeopleRepository
import com.fundtastic.mymovie.utils.AbsentLiveData
import javax.inject.Inject
import timber.log.Timber

class PersonDetailViewModel @Inject constructor(
  private val repository: PeopleRepository
) : ViewModel() {

  private val personIdLiveData: MutableLiveData<Int> = MutableLiveData()
  val personLiveData: LiveData<Resource<PersonDetail>>

  init {
    Timber.d("Injection : PersonDetailViewModel")

    personLiveData = personIdLiveData.switchMap {
      personIdLiveData.value?.let {
        repository.loadPersonDetail(it)
      } ?: AbsentLiveData.create()
    }
  }

  fun postPersonId(id: Int) = personIdLiveData.postValue(id)
}
