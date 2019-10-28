package com.zhuzichu.android.shared.fountain.rx.adapter

import androidx.annotation.WorkerThread
import com.zhuzichu.android.shared.fountain.ListResponse
import com.zhuzichu.android.shared.fountain.adapter.BasePageFetcher
import com.zhuzichu.android.shared.fountain.adapter.NetworkResultListener
import com.zhuzichu.android.shared.fountain.common.BaseNetworkDataSourceAdapterFactory
import com.zhuzichu.android.shared.fountain.common.notifyFromCallable


private fun <T : ListResponse<*>> RxPageFetcher<T>.toBasePageFetcher() = object :
  BasePageFetcher<T> {
  @WorkerThread
  override fun fetchPage(page: Int, pageSize: Int, networkResultListener: NetworkResultListener<T>) {
    networkResultListener.notifyFromCallable {
      fetchPage(page = page, pageSize = pageSize).blockingGet()
    }
  }
}

internal fun <T : ListResponse<*>> RxNetworkDataSourceAdapter<T>.toBaseNetworkDataSourceAdapter() =
    BaseNetworkDataSourceAdapterFactory.createFromAdapter(pageFetcher.toBasePageFetcher(), this)

private fun <T : ListResponse<*>> NotPagedRxPageFetcher<T>.toBasePageFetcher() = object : BasePageFetcher<T> {
  @WorkerThread
  override fun fetchPage(page: Int, pageSize: Int, networkResultListener: NetworkResultListener<T>) {
    networkResultListener.notifyFromCallable { this@toBasePageFetcher.fetchData().blockingGet() }
  }
}

internal fun <T : ListResponse<*>> NotPagedRxPageFetcher<T>.toBaseNetworkDataSourceAdapter() =
    BaseNetworkDataSourceAdapterFactory.createFromNotPagedPageFetcher(toBasePageFetcher())
