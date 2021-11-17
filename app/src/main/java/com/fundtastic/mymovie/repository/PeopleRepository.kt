package com.fundtastic.mymovie.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fundtastic.mymovie.api.ApiResponse
import com.fundtastic.mymovie.api.PeopleService
import com.fundtastic.mymovie.mappers.PeopleResponseMapper
import com.fundtastic.mymovie.mappers.PersonDetailResponseMapper
import com.fundtastic.mymovie.models.Resource
import com.fundtastic.mymovie.models.entity.Person
import com.fundtastic.mymovie.models.network.PeopleResponse
import com.fundtastic.mymovie.models.network.PersonDetail
import com.fundtastic.mymovie.room.PeopleDao
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
class PeopleRepository @Inject constructor(
  val peopleService: PeopleService,
  val peopleDao: PeopleDao
) : Repository {

  init {
    Timber.d("Injection PeopleRepository")
  }

  fun loadPeople(page: Int): LiveData<Resource<List<Person>>> {
    return object : NetworkBoundRepository<List<Person>, PeopleResponse, PeopleResponseMapper>() {
      override fun saveFetchData(items: PeopleResponse) {
        for (item in items.results) {
          item.page = page
        }
        peopleDao.insertPeople(items.results)
      }

      override fun shouldFetch(data: List<Person>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Person>> {
        return peopleDao.getPeople(page_ = page)
      }

      override fun fetchService(): LiveData<ApiResponse<PeopleResponse>> {
        return peopleService.fetchPopularPeople(page = page)
      }

      override fun mapper(): PeopleResponseMapper {
        return PeopleResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("onFetchFailed : $message")
      }
    }.asLiveData()
  }

  fun loadPersonDetail(id: Int): LiveData<Resource<PersonDetail>> {
    return object : NetworkBoundRepository<PersonDetail, PersonDetail, PersonDetailResponseMapper>() {
      override fun saveFetchData(items: PersonDetail) {
        val person = peopleDao.getPerson(id_ = id)
        person.personDetail = items
        peopleDao.updatePerson(person = person)
      }

      override fun shouldFetch(data: PersonDetail?): Boolean {
        return data == null || data.biography.isEmpty()
      }

      override fun loadFromDb(): LiveData<PersonDetail> {
        val person = peopleDao.getPerson(id_ = id)
        val data: MutableLiveData<PersonDetail> = MutableLiveData()
        data.postValue(person.personDetail)
        return data
      }

      override fun fetchService(): LiveData<ApiResponse<PersonDetail>> {
        return peopleService.fetchPersonDetail(id = id)
      }

      override fun mapper(): PersonDetailResponseMapper {
        return PersonDetailResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("onFetchFailed : $message")
      }
    }.asLiveData()
  }
}
