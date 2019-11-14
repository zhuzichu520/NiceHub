package com.zhuzichu.android.nicehub.ui.profile.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.repository.entity.BeanUser
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetUser @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<Unit, Flowable<BeanUser>>() {

    override fun execute(parameters: Unit): Flowable<BeanUser> {
        return remoteRepository.getUserInfo(null)
            .bindToSchedulers()
            .bindToException()
    }
}