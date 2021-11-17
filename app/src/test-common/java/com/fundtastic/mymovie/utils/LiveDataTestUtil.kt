package com.fundtastic.mymovie.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataTestUtil {
  @Suppress("UNCHECKED_CAST")
  @Throws(InterruptedException::class)
  fun <T> getValue(liveData: LiveData<T>): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
      override fun onChanged(type: T?) {
        data[0] = type
        latch.countDown()
        liveData.removeObserver(this)
      }
    }
    liveData.observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)
    return data[0] as T
  }
}
