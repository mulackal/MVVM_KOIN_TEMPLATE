package com.mvvm.mykotlinkoin_template.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val avatar: String = ""
)