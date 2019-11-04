package com.zhuzichu.android.nicehub.ui.feeds.repository.fragment

import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentRepositoryBinding
import com.zhuzichu.android.nicehub.ui.feeds.repository.entiy.ArgumentRepository
import com.zhuzichu.android.nicehub.ui.feeds.repository.viewmodel.ViewModelRepository
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase

class FragmentRepository :
    FragmentAnalyticsBase<ArgumentRepository, FragmentRepositoryBinding, ViewModelRepository>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_repository

    override fun initView() {
        super.initView()
        viewModel.bean.value = argument.bean
    }

    override fun initFirstData() {
        viewModel.loadReadme()
    }
}