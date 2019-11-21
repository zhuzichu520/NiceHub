package com.zhuzichu.android.nicehub.ui.setting.animation.fragment

import androidx.databinding.library.baseAdapters.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentAnimationBinding
import com.zhuzichu.android.nicehub.ui.setting.animation.viewmodel.ViewModelAnimation
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase

class FragmentAnimation : FragmentAnalyticsBase<FragmentAnimationBinding, ViewModelAnimation>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_animation

    override fun initFirstData() {
        super.initFirstData()
        viewModel.updateData()
    }
}