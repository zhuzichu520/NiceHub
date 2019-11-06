package com.zhuzichu.android.nicehub.ui.repositories.detail.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.ui.repositories.detail.entiy.ParamterGetReadme
import com.zhuzichu.android.shared.extension.NOT_FOUND
import com.zhuzichu.android.shared.extension.ResponseThrowable
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
            .map {
                getReadmeFromHtml(it)
            }
            .bindToSchedulers()
            .bindToException()
    }

    private fun getReadmeFromHtml(str: String): String {
        val readme = Jsoup.parse(str).getElementById("readme")
            ?: throw ResponseThrowable(NullPointerException(), NOT_FOUND).apply {
                message = "no readme"
            }
        return readme.outerHtml()
    }

}