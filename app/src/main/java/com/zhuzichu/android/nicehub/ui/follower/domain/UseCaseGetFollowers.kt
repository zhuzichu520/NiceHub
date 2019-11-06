package com.zhuzichu.android.nicehub.ui.follower.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.repository.entity.BeanFollower
import com.zhuzichu.android.nicehub.ui.follower.entity.ParamterGetFollowers
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetFollowers @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<ParamterGetFollowers, Flowable<List<BeanFollower>>>() {

    override fun execute(parameters: ParamterGetFollowers): Flowable<List<BeanFollower>> {
        return remoteRepository.getFollowers(
            parameters.follower,
            parameters.page,
            parameters.pageSize
        ).bindToSchedulers()
            .bindToException()
    }

}