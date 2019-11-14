package com.zhuzichu.android.nicehub.ui.repo.search.fragment

import androidx.databinding.library.baseAdapters.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentRepoSearchBinding
import com.zhuzichu.android.nicehub.ui.repo.search.viewmodel.ViewModelRepoSearch
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.showSoftKeyboard
import kotlinx.android.synthetic.main.fragment_repo_search.*

class FragmentRepoSearch :
    FragmentAnalyticsBase<FragmentRepoSearchBinding, ViewModelRepoSearch>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_repo_search

    override fun initView() {
        super.initView()
        search.showSoftKeyboard()
    }
}