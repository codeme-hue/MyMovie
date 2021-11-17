package com.fundtastic.mymovie.di

import com.fundtastic.mymovie.di.annotations.ActivityScope
import com.fundtastic.mymovie.view.ui.details.movie.MovieDetailActivity
import com.fundtastic.mymovie.view.ui.details.person.PersonDetailActivity
import com.fundtastic.mymovie.view.ui.details.tv.TvDetailActivity
import com.fundtastic.mymovie.view.ui.main.MainActivity
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
