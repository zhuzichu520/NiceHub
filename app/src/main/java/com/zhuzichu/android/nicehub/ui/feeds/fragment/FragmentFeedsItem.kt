package com.zhuzichu.android.nicehub.ui.feeds.fragment

import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentFeedsItemBinding
import com.zhuzichu.android.nicehub.ui.feeds.entity.ArgumentFeedsItem
import com.zhuzichu.android.nicehub.ui.feeds.viewmodel.ViewModelFeedsItem
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase

class FragmentFeedsItem :
    FragmentAnalyticsBase<ArgumentFeedsItem, FragmentFeedsItemBinding, ViewModelFeedsItem>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_feeds_item

    override fun initLazyData() {
        viewModel.loadData(argument.title)
    }
}