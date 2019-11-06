package com.zhuzichu.android.nicehub.ui.follower.viewmodel

import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel.ItemViewModelRepository
import com.zhuzichu.android.nicehub.ui.follower.domain.UseCaseGetFollowers
import com.zhuzichu.android.nicehub.ui.follower.entity.ParamterGetFollowers
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.itemDiffOf
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.widget.page.PageHelper
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import javax.inject.Inject

class ViewModelFollower @Inject constructor(
    private val useCaseGetFollowers: UseCaseGetFollowers
) : ViewModelAnalyticsBase() {

    lateinit var follower: String
    private val pageSize = 20

    private val pageHelper = PageHelper(
        AsyncDiffObservableList(itemDiffOf<ItemViewModelFollower> { oldItem, newItem -> oldItem.id == newItem.id }),
        this,
        pageSize,
        onLoadMore = {
            loadFollowers(it)
        }
    )

    val items = pageHelper.pageItems

    val onScrollBottom = pageHelper.onScrollBottom

    val onRefresh = pageHelper.onRefresh

    val itemBinding = pageHelper.itemBinding.apply {
        map<ItemViewModelFollower>(BR.item, R.layout.item_follower)
    }

    private fun loadFollowers(page: Int) {
        useCaseGetFollowers.execute(
            ParamterGetFollowers(follower, page, pageSize)
        )
    }
}