package com.zhuzichu.android.nicehub.ui.repo.search.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.repository.entity.BeanListRes
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.ui.repo.search.entity.ParamterSearchRepo
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseSearchRepo @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<ParamterSearchRepo, Flowable<BeanListRes<BeanRepository>>>() {

    override fun execute(parameters: ParamterSearchRepo): Flowable<BeanListRes<BeanRepository>> {
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