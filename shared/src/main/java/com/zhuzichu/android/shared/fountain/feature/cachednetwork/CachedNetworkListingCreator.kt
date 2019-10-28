package com.zhuzichu.android.shared.fountain.feature.cachednetwork


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zhuzichu.android.shared.fountain.ListResponse
import com.zhuzichu.android.shared.fountain.Listing
import com.zhuzichu.android.shared.fountain.adapter.BaseNetworkDataSourceAdapter
import com.zhuzichu.android.shared.fountain.adapter.CachedDataSourceAdapter
import java.util.concurrent.Executor

object CachedNetworkListingCreator {
  @Suppress("LongParameterList")
  fun <NetworkValue, DataSourceValue, ServiceResponse : ListResponse<out NetworkValue>> createListing(
      cachedDataSourceAdapter: CachedDataSourceAdapter<NetworkValue, DataSourceValue>,
      firstPage: Int,
      ioDatabaseExecutor: Executor,
      ioServiceExecutor: Executor,
      pagedListConfig: PagedList.Config,
      networkDataSourceAdapter: BaseNetworkDataSourceAdapter<ServiceResponse>
  ): Listing<DataSourceValue> {

    val boundaryCallback = BoundaryCallback(
        networkDataSourceAdapter = networkDataSourceAdapter,
        firstPage = firstPage,
        cachedDataSourceAdapter = cachedDataSourceAdapter,
        pagedListConfig = pagedListConfig,
        ioDatabaseExecutor = ioDatabaseExecutor,
        ioServiceExecutor = ioServiceExecutor
    )

    val builder = LivePagedListBuilder(cachedDataSourceAdapter.getDataSourceFactory(), pagedListConfig)
        .setBoundaryCallback(boundaryCallback)

    val refreshTrigger = MutableLiveData<Unit>()
    val refreshState = Transformations.switchMap(refreshTrigger) {
      boundaryCallback.resetData()
    }

    return Listing(
        pagedList = builder.build(),
        networkState = boundaryCallback.networkState,
        retry = {
          boundaryCallback.retry()
        },
        refresh = {
          refreshTrigger.value = null
        },
        refreshState = refreshState
    )
  }
}
