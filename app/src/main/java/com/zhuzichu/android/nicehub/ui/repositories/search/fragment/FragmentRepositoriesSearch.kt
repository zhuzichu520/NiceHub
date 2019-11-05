package com.zhuzichu.android.nicehub.ui.repositories.search.fragment

import androidx.databinding.library.baseAdapters.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentRepositoriesSearchBinding
import com.zhuzichu.android.nicehub.ui.repositories.search.viewmodel.ViewModelRepositoriesSearch
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.showSoftKeyboard
import kotlinx.android.synthetic.main.fragment_repositories_search.*

class FragmentRepositoriesSearch :
    FragmentAnalyticsBase<FragmentRepositoriesSearchBinding, ViewModelRepositoriesSearch>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_repositories_search

    override fun initView() {
        super.initView()
        search.showSoftKeyboard()
    }
}