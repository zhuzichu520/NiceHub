package com.zhuzichu.android.nicehub.ui.profile.fragment

import com.zhuzichu.android.mvvm.base.ArgumentDefault
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentProfileBinding
import com.zhuzichu.android.nicehub.ui.profile.viewmodel.ViewModelProfile
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase

class FragmentProfile :
    FragmentAnalyticsBase<ArgumentDefault, FragmentProfileBinding, ViewModelProfile>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_profile

    override fun initLazyData() {
        viewModel.updateUser()
    }

}