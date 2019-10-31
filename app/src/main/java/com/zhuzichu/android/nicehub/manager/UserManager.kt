package com.zhuzichu.android.nicehub.manager

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.nicehub.repository.entity.BeanUser

class UserManager {
    val user = MutableLiveData<BeanUser>()
}