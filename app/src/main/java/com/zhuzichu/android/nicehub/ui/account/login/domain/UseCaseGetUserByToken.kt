package com.zhuzichu.android.nicehub.ui.account.login.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.repository.entity.BeanUser
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetUserByToken @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<String, Flowable<BeanUser>>() {

    override fun execute(parameters: String): Flowable<BeanUser> {
        return remoteRepository.getUserInfo(parameters)
            .bindToSchedulers()
            .bindToException()
    }
}