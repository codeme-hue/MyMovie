package com.fundtastic.mymovie.di

import com.fundtastic.mymovie.compose.ViewModelActivity
import com.fundtastic.mymovie.compose.ViewModelFragment
import com.fundtastic.mymovie.di.annotations.ActivityScope
import com.fundtastic.mymovie.di.annotations.FragmentScope
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
