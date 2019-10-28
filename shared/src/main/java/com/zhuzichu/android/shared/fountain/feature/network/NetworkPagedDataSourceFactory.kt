package com.zhuzichu.android.shared.fountain.feature.network


import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.zhuzichu.android.shared.fountain.ListResponse
import com.zhuzichu.android.shared.fountain.adapter.BaseNetworkDataSourceAdapter
import java.util.concurrent.Executor

internal class NetworkPagedDataSourceFactory<T, ServiceResponse : ListResponse<out T>> (
    private val firstPage: Int,
    private val ioServiceExecutor: Executor,
    private val pagedListConfig: PagedList.Config,
    private val networkDataSourceAdapter: BaseNetworkDataSourceAdapter<ServiceResponse>
) : DataSource.Factory<Int, T>() {
  val sourceLiveData = MutableLiveData<NetworkPagedDataSource<T, ServiceResponse>>()
  private val resetDataList = MutableLiveData<ServiceResponse>()

  fun resetData() = sourceLiveData.value?.resetData(resetDataList)

  fun retry() = sourceLiveData.value?.retry?.invoke()

  override fun create(): DataSource<Int, T> {
    val source = NetworkPagedDataSource(
        firstPage = firstPage,
        ioServiceExecutor = ioServiceExecutor,
        pagedListConfig = pagedListConfig,
        networkDataSourceAdapter = networkDataSourceAdapter,
        initData = resetDataList.value
    )
    resetDataList.postValue(null)
    sourceLiveData.postValue(source)
    return source
  }
}
