package com.zhuzichu.android.nicehub.repository

import com.zhuzichu.android.nicehub.db.DaoUser
import com.zhuzichu.android.nicehub.db.entity.DOUser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LocalRepository {
    fun saveUser(user: DOUser)
}

class LocalRepositoryImpl(
    private val daoUser: DaoUser
) : LocalRepository {
    override fun saveUser(user: DOUser) {
        GlobalScope.launch {
            daoUser.insert(user)
        }
    }
}

