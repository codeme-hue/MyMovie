package com.fundtastic.mymovie.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.skydoves.themovies.TestTheMoviesApplication

@Suppress("unused")
class AndroidJunitTestRunner : AndroidJUnitRunner() {
  @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
  override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
    return super.newApplication(cl, TestTheMoviesApplication::class.java.name, context)
  }
}
