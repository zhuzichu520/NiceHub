package com.zhuzichu.android.nicehub.repository

import com.zhuzichu.android.nicehub.db.DaoUser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LocalRepository {
    fun  saveUser(user: DaoUser)
}

class LocalRepositoryImpl(
    private val daoUser: DaoUser
) : LocalRepository {
    override fun saveUser(user: DaoUser) {
        GlobalScope.launch {
            daoUser.insert(user)
        }
    }
}

