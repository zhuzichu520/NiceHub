package com.zhuzichu.android.nicehub.ui.feeds.repository.fragment

import com.zhuzichu.android.mvvm.base.ArgumentDefault
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentRepositoryBinding
import com.zhuzichu.android.nicehub.ui.feeds.repository.viewmodel.ViewModelRepository
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.logi

class FragmentRepository :
    FragmentAnalyticsBase<ArgumentDefault, FragmentRepositoryBinding, ViewModelRepository>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_repository
}