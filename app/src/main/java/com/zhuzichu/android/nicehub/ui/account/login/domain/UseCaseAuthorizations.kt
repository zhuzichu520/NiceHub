package com.zhuzichu.android.nicehub.ui.account.login.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.repository.entity.BeanAuthor
import com.zhuzichu.android.nicehub.ui.account.login.entity.ParamterAuthorizations
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseAuthorizations @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<String, Flowable<BeanAuthor>>() {

    override fun execute(parameters: String): Flowable<BeanAuthor> {
        return remoteRepository.authorizations(ParamterAuthorizations(), parameters)
            .bindToSchedulers()
            .bindToException()
    }
}