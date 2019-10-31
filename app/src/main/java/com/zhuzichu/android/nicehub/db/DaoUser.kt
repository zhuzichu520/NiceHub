package com.zhuzichu.android.nicehub.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhuzichu.android.nicehub.db.entity.DOUser
import io.reactivex.Flowable

interface DaoUser {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: DaoUser)

    @Delete
    suspend fun deleteUser(list: List<DOUser>)

    @Query("select * from user order by loginTime desc")
    fun queryUsers(): Flowable<List<DOUser>>

}