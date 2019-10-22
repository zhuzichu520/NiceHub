package com.zhuzichu.android.nicehub.ui.feeds.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.repository.entity.BeanListRes
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.ui.feeds.entity.ParamterGetHotRepos
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetHotRepos @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<ParamterGetHotRepos, Flowable<BeanListRes<BeanRepository>>>() {

    override fun execute(parameters: ParamterGetHotRepos): Flowable<BeanListRes<BeanRepository>> {
        return remoteRepository.getHotRepos(parameters.q, parameters.page)
            .bindToSchedulers()
            .bindToException()
    }
}