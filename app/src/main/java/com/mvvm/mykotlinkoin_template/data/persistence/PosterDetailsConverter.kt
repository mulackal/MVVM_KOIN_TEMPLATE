
package com.mvvm.mykotlinkoin_template.data.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mvvm.mykotlinkoin_template.data.model.User

class IntegerListConverter {

  @TypeConverter
  fun fromString(value: String): List<User>? {
    val listType = object : TypeToken<List<User>>() {}.type
    return Gson().fromJson<List<User>>(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<User>): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
