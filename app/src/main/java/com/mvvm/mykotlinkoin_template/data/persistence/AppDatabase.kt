
package com.mvvm.mykotlinkoin_template.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mvvm.mykotlinkoin_template.data.model.User


@Database(entities = [User::class], version = 1, exportSchema = true)
@TypeConverters(value = [IntegerListConverter::class])
abstract class AppDatabase : RoomDatabase() {

  abstract fun userDao(): UserDao
}
