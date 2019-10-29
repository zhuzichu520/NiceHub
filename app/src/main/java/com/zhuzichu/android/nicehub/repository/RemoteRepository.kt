package com.zhuzichu.android.nicehub.repository

import androidx.paging.PagedList
import com.zhuzichu.android.nicehub.repository.entity.BeanListRes
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel.ViewModelFeedsChild
import com.zhuzichu.android.shared.fountain.Listing
import com.zhuzichu.android.shared.fountain.rx.FountainRx
import com.zhuzichu.android.shared.fountain.rx.adapter.RxNetworkDataSourceAdapter
import com.zhuzichu.android.shared.fountain.rx.adapter.RxPageFetcher
import com.zhuzichu.android.shared.fountain.rx.adapter.toTotalEntityCountNetworkDataSourceAdapter
import io.reactivex.Single
import retrofit2.Retrofit

interface RemoteRepository {
    fun getHotRepos(
        query: String,
        page: Int,
        pageSize: Int
    ): Single<BeanListRes<BeanRepository>>

    fun getHotRepos(
        query: String,
        pagedListConfig: PagedList.Config,
        viewModelFeedsChild: ViewModelFeedsChild
    ): Listing<BeanRepository>
}

class RemoteRepositoryImpl(
    private val retrofit: Retrofit
) : RemoteRepository {

    private val githubApi by lazy { retrofit.create(GithubApi::class.java) }

    override fun getHotRepos(
        query: String,
        page: Int,
        pageSize: Int
    ): Single<BeanListRes<BeanRepository>> {
        return githubApi.searchRepositories(query, "stars", "desc", page, pageSize)
    }

    override fun getHotRepos(
        query: String,
        pagedListConfig: PagedList.Config,
        viewModelFeedsChild: ViewModelFeedsChild
    ): Listing<BeanRepository> {
        val networkDataSourceAdapter = createNetworkDataSourceAdapter(query)
        return FountainRx.createNetworkListing(
            networkDataSourceAdapter = networkDataSourceAdapter,
            pagedListConfig = pagedListConfig
        )
    }


    private fun createNetworkDataSourceAdapter(query: String): RxNetworkDataSourceAdapter<BeanListRes<BeanRepository>> {
        val pageFetcher = object : RxPageFetcher<BeanListRes<BeanRepository>> {
            override fun fetchPage(page: Int, pageSize: Int): Single<BeanListRes<BeanRepository>> =
                getHotRepos(query, page = page, pageSize = pageSize)
                    .map {

                    }
        }
        return pageFetcher.toTotalEntityCountNetworkDataSourceAdapter()
    }
}