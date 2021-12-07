package com.haekal.mymovie

import android.os.Build.FINGERPRINT
import com.facebook.stetho.Stetho
import com.haekal.mymovie.di.DaggerAppComponent
import dagger.android.DaggerApplication
import timber.log.Timber

@Suppress("unused")
class TheMoviesApplication : DaggerApplication() {

  private val appComponent = DaggerAppComponent.factory().create(this)

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

    if (!isRobolectricUnitTest()) {
      Stetho.initializeWithDefaults(this)
    }
  }

  private fun isRobolectricUnitTest(): Boolean {
    return "robolectric" == FINGERPRINT
  }

  override fun applicationInjector() = appComponent
}
