package com.zhuzichu.android.shared.fountain.feature.cachednetwork


import androidx.annotation.AnyThread
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.zhuzichu.android.shared.fountain.ListResponse
import com.zhuzichu.android.shared.fountain.NetworkState
import com.zhuzichu.android.shared.fountain.adapter.BaseNetworkDataSourceAdapter
import com.zhuzichu.android.shared.fountain.adapter.CachedDataSourceAdapter
import com.zhuzichu.android.shared.fountain.feature.PagerManager
import java.util.concurrent.Executor

internal class BoundaryCallback<NetworkValue, DataSourceValue, NetworkResponse : ListResponse<out NetworkValue>>(
    networkDataSourceAdapter: BaseNetworkDataSourceAdapter<NetworkResponse>,
    private val cachedDataSourceAdapter: CachedDataSourceAdapter<NetworkValue, DataSourceValue>,
    pagedListConfig: PagedList.Config,
    ioServiceExecutor: Executor,
    private val ioDatabaseExecutor: Executor,
    firstPage: Int
) : PagedList.BoundaryCallback<DataSourceValue>() {
  val networkState: LiveData<NetworkState>
    get() = pageManager.networkState

  fun retry() = pageManager.helper.retryAllFailed()

  private val pageManager = PagerManager(
      networkDataSourceAdapter = networkDataSourceAdapter,
      pagedListConfig = pagedListConfig,
      ioServiceExecutor = ioServiceExecutor,
      firstPage = firstPage
  )

  init {
    pageManager.loadInitialData(this::processResponse)
  }

  // ignored, since we are requesting the first page in the init method.
  override fun onZeroItemsLoaded() {}

  override fun onItemAtEndLoaded(itemAtEnd: DataSourceValue) = pageManager.loadNextPage(this::processResponse)

  // ignored, since we only ever append to what's in the DB
  override fun onItemAtFrontLoaded(itemAtFront: DataSourceValue) {}

  @AnyThread
  fun resetData(): LiveData<NetworkState> = pageManager.resetData(this::processResponse)

  private fun processResponse(
      request: PagerManager.Request,
      response: NetworkResponse,
      callback: PagerManager.Callback
  ) {
    ioDatabaseExecutor.execute {
      @Suppress("TooGenericExceptionCaught")
      try {
        cachedDataSourceAdapter.runInTransaction {
          if (request.isFirstPage) {
            cachedDataSourceAdapter.dropEntities()
          }
          cachedDataSourceAdapter.saveEntities(response.getElements())
        }

        callback.onSuccess()
      } catch (throwable: Throwable) {
        callback.onError(throwable)
      }
    }
  }
}
