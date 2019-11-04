package com.zhuzichu.android.nicehub.ui.feeds.repository.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.ui.feeds.repository.entiy.ParamterGetReadme
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import org.jsoup.Jsoup
import javax.inject.Inject

class UseCaseGetReadme @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<ParamterGetReadme, Flowable<String>>() {

    override fun execute(parameters: ParamterGetReadme): Flowable<String> {
        return remoteRepository.getRepositoryReadme(parameters.login, parameters.name)
            .bindToSchedulers()
            .bindToException()
            .map {
                getReadmeFromHtml(it)
            }
    }

    private fun getReadmeFromHtml(str: String): String {
        return Jsoup.parse(str).getElementById("readme").outerHtml()
    }

}