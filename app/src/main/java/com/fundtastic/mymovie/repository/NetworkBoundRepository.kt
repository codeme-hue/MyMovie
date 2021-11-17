package com.fundtastic.mymovie.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.fundtastic.mymovie.api.ApiResponse
import com.fundtastic.mymovie.compose.AppExecutors
import com.fundtastic.mymovie.mappers.NetworkResponseMapper
import com.fundtastic.mymovie.models.NetworkResponseModel
import com.fundtastic.mymovie.models.Resource
import timber.log.Timber

abstract class NetworkBoundRepository<ResultType,
  RequestType : NetworkResponseModel,
  Mapper : NetworkResponseMapper<RequestType>>
internal constructor() {

  private val result: MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

  init {
    Timber.d("Injection NetworkBoundRepository")
    AppExecutors.diskIO().execute {
      val loadedFromDB = this.loadFromDb()
      AppExecutors.mainThread().execute {
        result.addSource(loadedFromDB) { data ->
          result.removeSource(loadedFromDB)
          if (shouldFetch(data)) {
            result.postValue(Resource.loading(null))
            fetchFromNetwork(loadedFromDB)
          } else {
            result.addSource<ResultType>(loadedFromDB) { newData ->
              setValue(Resource.success(newData))
            }
          }
        }
      }
    }
  }

  private fun fetchFromNetwork(loadedFromDB: LiveData<ResultType>) {
    val apiResponse = fetchService()
    result.addSource(apiResponse) { response ->
      response?.let {
        when (response.isSuccessful) {
          true -> {
            AppExecutors.diskIO().execute {
              response.body?.let {
                saveFetchData(it)
                val loaded = loadFromDb()
                AppExecutors.mainThread().execute {
                  result.addSource(loaded) { newData ->
                    newData?.let {
                      setValue(Resource.success(newData))
                    }
                  }
                }
              }
            }
          }
          false -> {
            AppExecutors.mainThread().execute {
              result.removeSource(loadedFromDB)
              onFetchFailed(response.message)
              response.message?.let {
                result.addSource<ResultType>(loadedFromDB) { newData ->
                  setValue(Resource.error(it, newData))
                }
              }
            }
          }
        }
      }
    }
  }

  @MainThread
  private fun setValue(newValue: Resource<ResultType>) {
    result.value = newValue
  }

  fun asLiveData(): LiveData<Resource<ResultType>> {
    return result
  }

  @MainThread
  protected abstract fun shouldFetch(data: ResultType?): Boolean

  @WorkerThread
  protected abstract fun loadFromDb(): LiveData<ResultType>

  @MainThread
  protected abstract fun fetchService(): LiveData<ApiResponse<RequestType>>

  @MainThread
  protected abstract fun mapper(): Mapper

  @WorkerThread
  protected abstract fun saveFetchData(items: RequestType)

  @MainThread
  protected abstract fun onFetchFailed(message: String?)
}
