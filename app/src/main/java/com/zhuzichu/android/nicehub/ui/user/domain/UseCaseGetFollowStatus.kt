package com.zhuzichu.android.nicehub.ui.user.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetFollowStatus @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<String, Flowable<Boolean>>() {

    override fun execute(parameters: String): Flowable<Boolean> {
        return remoteRepository.getFollowStatus(parameters)
            .bindToSchedulers()
            .bindToException()
    }

}
