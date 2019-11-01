package com.zhuzichu.android.nicehub.ui.repositories.search.fragment

import androidx.databinding.library.baseAdapters.BR
import com.zhuzichu.android.mvvm.base.ArgumentDefault
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentRepositoriesSearchBinding
import com.zhuzichu.android.nicehub.ui.repositories.search.viewmodel.ViewModelRepositoriesSearch
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase

class FragmentRepositoriesSearch :
    FragmentAnalyticsBase<ArgumentDefault, FragmentRepositoriesSearchBinding, ViewModelRepositoriesSearch>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_repositories_search

    override fun initLazyData() {
    }
}