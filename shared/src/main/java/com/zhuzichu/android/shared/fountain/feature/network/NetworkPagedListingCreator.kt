package com.zhuzichu.android.shared.fountain.feature.network

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zhuzichu.android.shared.fountain.ListResponse
import com.zhuzichu.android.shared.fountain.Listing
import com.zhuzichu.android.shared.fountain.adapter.BaseNetworkDataSourceAdapter
import java.util.concurrent.Executor

object NetworkPagedListingCreator {
  fun <Value, ServiceResponse : ListResponse<out Value>> createListing(
      firstPage: Int,
      ioServiceExecutor: Executor,
      pagedListConfig: PagedList.Config,
      networkDataSourceAdapter: BaseNetworkDataSourceAdapter<ServiceResponse>
  ): Listing<Value> {
    val sourceFactory = NetworkPagedDataSourceFactory(
        firstPage = firstPage,
        ioServiceExecutor = ioServiceExecutor,
        pagedListConfig = pagedListConfig,
        networkDataSourceAdapter = networkDataSourceAdapter
    )
    val livePagedList = LivePagedListBuilder(sourceFactory, pagedListConfig)
        .build()

    val refreshTrigger = MutableLiveData<Unit>()
    val refreshState = Transformations.switchMap(refreshTrigger) {
      sourceFactory.resetData()
    }

    return Listing(
        pagedList = livePagedList,
        networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
          it.networkState
        },
        retry = {
          sourceFactory.retry()
        },
        refresh = {
          refreshTrigger.value = null
        },
        refreshState = refreshState
    )
  }
}
