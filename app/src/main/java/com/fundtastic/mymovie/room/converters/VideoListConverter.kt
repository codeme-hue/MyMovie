package com.fundtastic.mymovie.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.fundtastic.mymovie.models.Video

open class VideoListConverter {
  @TypeConverter
  fun fromString(value: String): List<Video>? {
    val listType = object : TypeToken<List<Video>>() {}.type
    return Gson().fromJson<List<Video>>(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<Video>?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
