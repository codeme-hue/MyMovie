package com.fundtastic.mymovie.di

import com.fundtastic.mymovie.di.annotations.FragmentScope
import com.fundtastic.mymovie.view.ui.main.MovieListFragment
import com.fundtastic.mymovie.view.ui.main.PersonListFragment
import com.fundtastic.mymovie.view.ui.main.TvListFragment
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
