

package com.mvvm.mykotlinkoin_template.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvvm.mykotlinkoin_template.data.model.User

@Dao
interface UserDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertUserList(posters: List<User>)

  @Query("SELECT * FROM User WHERE id = :id_")
  fun getUser(id_: Int): User

  @Query("SELECT * FROM User")
  fun getUserList(): List<User>
}
