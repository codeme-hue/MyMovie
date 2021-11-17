package com.fundtastic.mymovie.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fundtastic.mymovie.di.annotations.ViewModelKey
import com.fundtastic.mymovie.view.ui.details.movie.MovieDetailViewModel
import com.fundtastic.mymovie.view.ui.details.person.PersonDetailViewModel
import com.fundtastic.mymovie.view.ui.details.tv.TvDetailViewModel
import com.fundtastic.mymovie.view.ui.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainActivityViewModel::class)
  internal abstract fun bindMainActivityViewModels(mainActivityViewModel: MainActivityViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(MovieDetailViewModel::class)
  internal abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(TvDetailViewModel::class)
  internal abstract fun bindTvDetailViewModel(tvDetailViewModel: TvDetailViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(PersonDetailViewModel::class)
  internal abstract fun bindPersonDetailViewModel(personDetailViewModel: PersonDetailViewModel): ViewModel

  @Binds
  internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
