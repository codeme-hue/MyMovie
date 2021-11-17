package com.fundtastic.mymovie.api

import androidx.lifecycle.LiveData
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import retrofit2.CallAdapter
import retrofit2.Retrofit

class LiveDataCallAdapterFactory : CallAdapter.Factory() {

  override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): LiveDataCallAdapter<*>? {
    if (getRawType(returnType) != LiveData::class.java) {
      return null
    }
    val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
    val rawObservableType = getRawType(observableType)
    require(rawObservableType == ApiResponse::class.java) { "type must be a resource" }
    require(observableType is ParameterizedType) { "resource must be parameterized" }
    val bodyType = getParameterUpperBound(0, observableType)
    return LiveDataCallAdapter<Type>(bodyType)
  }
}
