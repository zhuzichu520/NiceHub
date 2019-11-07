package com.zhuzichu.android.nicehub.manager

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.nicehub.repository.entity.BeanUser

class UserManager {
    val user = MutableLiveData<BeanUser>()

    fun isLogin(): Boolean {
        return user.value != null
    }

    fun doLogin(closure: () -> Unit): UserManager {
        if (isLogin()) {
            closure.invoke()
        }
        return this
    }

    fun doNoLogin(closure: () -> Unit): UserManager {
        if (!isLogin()) {
            closure.invoke()
        }
        return this
    }
}