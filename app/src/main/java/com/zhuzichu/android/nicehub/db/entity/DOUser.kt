package com.zhuzichu.android.nicehub.db.entity

import androidx.room.Entity
import androidx.room.Index
import java.util.*

@Entity(
    tableName = "user",
    indices = [Index("id", "login")],
    primaryKeys = ["id","login"]
)
data class DOUser(
    var id: Long,
    var login: String,
    var avatar: String?,
    var name: String?,
    val loginTime: Calendar = Calendar.getInstance()
)