package com.zhuzichu.android.shared.fountain.common

import com.zhuzichu.android.shared.fountain.adapter.BaseNetworkDataSourceAdapter
import com.zhuzichu.android.shared.fountain.adapter.BasePageFetcher
import com.zhuzichu.android.shared.fountain.adapter.NetworkDataSourceAdapter


object BaseNetworkDataSourceAdapterFactory {
  fun <T> createFromAdapter(
      basePageFetcher: BasePageFetcher<T>,
      networkDataSourceAdapter: NetworkDataSourceAdapter<*>
  ) = createNetworkDataSourceAdapter(basePageFetcher) { page, pageSize ->
    networkDataSourceAdapter.canFetch(page, pageSize)
  }

  fun <T> createFromNotPagedPageFetcher(
      basePageFetcher: BasePageFetcher<T>
  ) = createNetworkDataSourceAdapter(basePageFetcher) { page, _ ->
    FountainConstants.DEFAULT_FIRST_PAGE == page
  }

  private fun <T> createNetworkDataSourceAdapter(
      basePageFetcher: BasePageFetcher<T>,
      canFetch: (page: Int, pageSize: Int) -> Boolean
  ): BaseNetworkDataSourceAdapter<T> = object : BaseNetworkDataSourceAdapter<T> {
    override val pageFetcher = basePageFetcher

    override fun canFetch(page: Int, pageSize: Int) = canFetch.invoke(page, pageSize)
  }
}
