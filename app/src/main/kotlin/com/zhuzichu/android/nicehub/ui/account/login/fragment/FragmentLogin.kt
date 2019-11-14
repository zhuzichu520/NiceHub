package com.zhuzichu.android.nicehub.ui.account.login.fragment

import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentLoginBinding
import com.zhuzichu.android.nicehub.ui.account.login.viewmodel.ViewModelLogin
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase


class FragmentLogin : FragmentAnalyticsBase<FragmentLoginBinding, ViewModelLogin>() {

    override fun setLayoutId(): Int = R.layout.fragment_login

    override fun bindVariableId(): Int = BR.viewModel

}