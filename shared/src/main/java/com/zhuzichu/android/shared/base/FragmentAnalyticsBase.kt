package com.zhuzichu.android.shared.base

import androidx.databinding.ViewDataBinding
import com.zhuzichu.android.mvvm.base.BaseArgument
import com.zhuzichu.android.mvvm.base.BaseFragment
import com.zhuzichu.android.mvvm.base.BaseViewModel

abstract class FragmentAnalyticsBase<TArgument : BaseArgument, TBinding : ViewDataBinding, TViewModel : BaseViewModel> :
    BaseFragment<TArgument, TBinding, TViewModel>()