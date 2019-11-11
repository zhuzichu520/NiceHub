package com.zhuzichu.android.nicehub.ui.user.fragment

import androidx.navigation.fragment.navArgs
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentUserBinding
import com.zhuzichu.android.nicehub.ui.repo.detail.fragment.FragmentRepoDetailArgs
import com.zhuzichu.android.nicehub.ui.user.viewmodel.ViewModelUser
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase

class FragmentUser : FragmentAnalyticsBase<FragmentUserBinding, ViewModelUser>() {

    private val args: FragmentRepoDetailArgs by navArgs()

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_user

    override fun initView() {
        super.initView()

    }
}