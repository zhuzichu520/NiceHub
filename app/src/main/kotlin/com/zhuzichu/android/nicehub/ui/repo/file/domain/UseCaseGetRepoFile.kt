package com.zhuzichu.android.nicehub.ui.repo.file.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.repository.entity.BeanFile
import com.zhuzichu.android.nicehub.ui.repo.file.entity.ParamterGetRepoFile
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetRepoFile @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<ParamterGetRepoFile, Flowable<List<BeanFile>>>() {

    override fun execute(parameters: ParamterGetRepoFile): Flowable<List<BeanFile>> {
        return remoteRepository.getFiles(
            parameters.login,
            parameters.name,
            parameters.path,
            parameters.ref
        )
    }

}