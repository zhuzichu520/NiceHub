package com.zhuzichu.android.nicehub.ui.setting.main.fragment

import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentSettingBinding
import com.zhuzichu.android.nicehub.ui.setting.main.viewmodel.ViewModelSetting
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase


class FragmentSetting :
    FragmentAnalyticsBase<FragmentSettingBinding, ViewModelSetting>() {

    override fun setLayoutId(): Int = R.layout.fragment_setting

    override fun bindVariableId(): Int = BR.viewModel

}