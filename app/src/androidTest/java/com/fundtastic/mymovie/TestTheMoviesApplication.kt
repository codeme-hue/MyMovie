package com.fundtastic.mymovie

import com.fundtastic.mymovie.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TestTheMoviesApplication : DaggerApplication() {

  private val appComponent = DaggerAppComponent.builder()
      .application(this)
      .build()

  override fun onCreate() {
    super.onCreate()
    appComponent.inject(this)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return appComponent
  }
}
