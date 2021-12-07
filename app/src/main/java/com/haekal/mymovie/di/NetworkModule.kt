package com.haekal.mymovie.di

import androidx.annotation.NonNull
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.haekal.mymovie.api.LiveDataCallAdapterFactory
import com.haekal.mymovie.api.MovieService
import com.haekal.mymovie.api.PeopleService
import com.haekal.mymovie.api.RequestInterceptor
import com.haekal.mymovie.api.TheDiscoverService
import com.haekal.mymovie.api.TvService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

  @Provides
  @Singleton
  fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(RequestInterceptor())
      .addNetworkInterceptor(StethoInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("https://api.themoviedb.org/")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(LiveDataCallAdapterFactory())
      .build()
  }

  @Provides
  @Singleton
  fun provideDiscoverService(@NonNull retrofit: Retrofit): TheDiscoverService {
    return retrofit.create(TheDiscoverService::class.java)
  }

  @Provides
  @Singleton
  fun providePeopleService(@NonNull retrofit: Retrofit): PeopleService {
    return retrofit.create(PeopleService::class.java)
  }

  @Provides
  @Singleton
  fun provideMovieService(@NonNull retrofit: Retrofit): MovieService {
    return retrofit.create(MovieService::class.java)
  }

  @Provides
  @Singleton
  fun provideTvService(@NonNull retrofit: Retrofit): TvService {
    return retrofit.create(TvService::class.java)
  }
}
