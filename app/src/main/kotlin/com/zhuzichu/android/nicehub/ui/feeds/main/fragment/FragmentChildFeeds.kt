package com.zhuzichu.android.nicehub.ui.feeds.main.fragment

import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentChildFeedsBinding
import com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel.ViewModelFeedsChild
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.bindArgument

class FragmentChildFeeds :
    FragmentAnalyticsBase<FragmentChildFeedsBinding, ViewModelFeedsChild>() {

    val title: String? by bindArgument()

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_child_feeds

    override fun initView() {
        viewModel.title.value = title
    }

}