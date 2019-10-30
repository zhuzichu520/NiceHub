package com.zhuzichu.android.nicehub.ui.account.login.fragment

import com.zhuzichu.android.mvvm.base.BaseFragment
import com.zhuzichu.android.mvvm.base.ArgumentDefault
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentLoginBinding
import com.zhuzichu.android.nicehub.ui.account.login.viewmodel.ViewModelLogin


class FragmentLogin : BaseFragment<ArgumentDefault, FragmentLoginBinding, ViewModelLogin>() {

    override fun setLayoutId(): Int = R.layout.fragment_login

    override fun bindVariableId(): Int = BR.viewModel

}