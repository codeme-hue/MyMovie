package com.haekal.mymovie.di

import com.haekal.mymovie.di.annotations.FragmentScope
import com.haekal.mymovie.view.ui.main.MovieListFragment
import com.haekal.mymovie.view.ui.main.PersonListFragment
import com.haekal.mymovie.view.ui.main.TvListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModule {

  @FragmentScope
  @ContributesAndroidInjector
  abstract fun contributeMovieListFragment(): MovieListFragment

  @FragmentScope
  @ContributesAndroidInjector
  abstract fun contributeTvListFragment(): TvListFragment

  @FragmentScope
  @ContributesAndroidInjector
  abstract fun contributePersonListFragment(): PersonListFragment
}
