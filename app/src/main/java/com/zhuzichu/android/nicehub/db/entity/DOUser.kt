package com.zhuzichu.android.nicehub.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "user",
    indices = [Index("id", "login")]
)
data class DOUser(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @PrimaryKey
    var login: String,
    var name: String,
    val loginTime: Calendar = Calendar.getInstance()
)