package com.fundtastic.mymovie.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.fundtastic.mymovie.room.AppDatabase
import com.fundtastic.mymovie.room.MovieDao
import com.fundtastic.mymovie.room.PeopleDao
import com.fundtastic.mymovie.room.TvDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

  @Provides
  @Singleton
  fun provideDatabase(@NonNull application: Application): AppDatabase {
    return Room
      .databaseBuilder(application, AppDatabase::class.java, "TheMovies.db")
      .build()
  }

  @Provides
  @Singleton
  fun provideMovieDao(@NonNull database: AppDatabase): MovieDao {
    return database.movieDao()
  }

  @Provides
  @Singleton
  fun provideTvDao(@NonNull database: AppDatabase): TvDao {
    return database.tvDao()
  }

  @Provides
  @Singleton
  fun providePeopleDao(@NonNull database: AppDatabase): PeopleDao {
    return database.peopleDao()
  }
}
