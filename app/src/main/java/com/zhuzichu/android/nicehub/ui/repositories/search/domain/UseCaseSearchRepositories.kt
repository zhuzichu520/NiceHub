package com.zhuzichu.android.nicehub.ui.repositories.search.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.repository.entity.BeanListRes
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.ui.repositories.search.entity.ParamterSearchRepositories
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseSearchRepositories @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<ParamterSearchRepositories, Flowable<BeanListRes<BeanRepository>>>() {

    override fun execute(parameters: ParamterSearchRepositories): Flowable<BeanListRes<BeanRepository>> {
        return remoteRepository.searchRepositories(
            parameters.q,
            parameters.sort,
            parameters.order,
            parameters.page,
            parameters.pageSize
        ).bindToSchedulers()
            .bindToException()
    }
}