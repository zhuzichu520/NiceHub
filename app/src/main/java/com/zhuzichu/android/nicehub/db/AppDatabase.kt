package com.zhuzichu.android.nicehub.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zhuzichu.android.nicehub.db.entity.DOUser
import com.zhuzichu.android.shared.global.AppGlobal.context

@Database(entities = [DOUser::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun daoUser(): DaoUser

    companion object {
        private const val DATABASE_NAME = "db-nicehub"

        fun getInstance(): AppDatabase {
            return buildDatabase()
        }

        private fun buildDatabase(): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {

                })
                .build()
        }
    }
}
