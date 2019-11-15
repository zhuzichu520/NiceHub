package com.zhuzichu.android.nicehub.ui.user.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import retrofit2.Response
import javax.inject.Inject

class UseCaseGetFollowStatus @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<String, Flowable<Response<Any>>>() {

    override fun execute(parameters: String): Flowable<Response<Any>> {
        return remoteRepository.getFollowStatus(parameters)
            .bindToSchedulers()
            .bindToException()
    }

}
