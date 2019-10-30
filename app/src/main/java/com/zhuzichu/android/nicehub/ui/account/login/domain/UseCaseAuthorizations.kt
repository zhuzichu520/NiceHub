package com.zhuzichu.android.nicehub.ui.account.login.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.ui.account.login.entity.ParamterAuthorizations
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseAuthorizations @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<Unit, Flowable<String>>() {

    override fun execute(parameters: Unit): Flowable<String> {
        return remoteRepository.authorizations(ParamterAuthorizations())
            .bindToSchedulers()
            .bindToException()
    }
}