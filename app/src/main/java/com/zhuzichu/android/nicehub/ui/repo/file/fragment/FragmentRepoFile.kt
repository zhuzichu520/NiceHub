package com.zhuzichu.android.nicehub.ui.repo.file.fragment

import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentRepoFileBinding
import com.zhuzichu.android.nicehub.ui.repo.file.viewmodel.ViewModelRepoFile
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase

class FragmentRepoFile : FragmentAnalyticsBase<FragmentRepoFileBinding, ViewModelRepoFile>() {

    override fun setLayoutId(): Int = R.layout.fragment_repo_file

    override fun bindVariableId(): Int = BR.viewModel
}