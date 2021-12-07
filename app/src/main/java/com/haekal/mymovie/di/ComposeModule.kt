package com.haekal.mymovie.di

import com.haekal.mymovie.compose.ViewModelActivity
import com.haekal.mymovie.compose.ViewModelFragment
import com.haekal.mymovie.di.annotations.ActivityScope
import com.haekal.mymovie.di.annotations.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ComposeModule {

  @ActivityScope
  @ContributesAndroidInjector
  internal abstract fun contributeViewModelActivity(): ViewModelActivity

  @FragmentScope
  @ContributesAndroidInjector
  internal abstract fun contributeViewModelFragment(): ViewModelFragment
}
