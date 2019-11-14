package com.zhuzichu.android.nicehub.ui.profile.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import org.jsoup.Jsoup
import javax.inject.Inject

class UseCaseGetContributions @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<String, Flowable<String>>() {

    override fun execute(parameters: String): Flowable<String> {
        return remoteRepository.getContributions(parameters)
            .bindToSchedulers()
            .bindToException()
            .map {
                getContributionsSvgFromHtml(it)
            }
    }

    private fun getContributionsSvgFromHtml(html: String): String {
        val svg = Jsoup.parse(html).getElementsByClass("js-calendar-graph-svg")[0]
        svg.select(".wday").remove()
        return svg.outerHtml()
    }
}