package com.haekal.mymovie.di

import com.haekal.mymovie.di.annotations.ActivityScope
import com.haekal.mymovie.view.ui.details.movie.MovieDetailActivity
import com.haekal.mymovie.view.ui.details.person.PersonDetailActivity
import com.haekal.mymovie.view.ui.details.tv.TvDetailActivity
import com.haekal.mymovie.view.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

  @ActivityScope
  @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
  internal abstract fun contributeMainActivity(): MainActivity

  @ActivityScope
  @ContributesAndroidInjector
  internal abstract fun contributeMovieDetailActivity(): MovieDetailActivity

  @ActivityScope
  @ContributesAndroidInjector
  internal abstract fun contributeTvDetailActivity(): TvDetailActivity

  @ActivityScope
  @ContributesAndroidInjector
  internal abstract fun contributePersonDetailActivity(): PersonDetailActivity
}
