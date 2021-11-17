package com.fundtastic.mymovie.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fundtastic.mymovie.models.entity.Movie
import com.fundtastic.mymovie.models.entity.Person
import com.fundtastic.mymovie.models.entity.Tv
import com.fundtastic.mymovie.room.converters.IntegerListConverter
import com.fundtastic.mymovie.room.converters.KeywordListConverter
import com.fundtastic.mymovie.room.converters.ReviewListConverter
import com.fundtastic.mymovie.room.converters.StringListConverter
import com.fundtastic.mymovie.room.converters.VideoListConverter

@Database(entities = [(Movie::class), (Tv::class), (Person::class)],
  version = 3, exportSchema = false)
@TypeConverters(value = [(StringListConverter::class), (IntegerListConverter::class),
  (KeywordListConverter::class), (VideoListConverter::class), (ReviewListConverter::class)])
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
  abstract fun tvDao(): TvDao
  abstract fun peopleDao(): PeopleDao
}
