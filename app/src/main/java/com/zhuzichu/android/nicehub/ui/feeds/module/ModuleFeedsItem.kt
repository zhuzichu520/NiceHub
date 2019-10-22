package com.zhuzichu.android.nicehub.ui.feeds.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.feeds.fragment.FragmentFeedsItem
import com.zhuzichu.android.nicehub.ui.feeds.viewmodel.ViewModelFeedsItem
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleFeedsItem {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentFeedsItem(): FragmentFeedsItem

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelFeedsItem::class)
    abstract fun bindViewModelFeedsItem(viewModel: ViewModelFeedsItem): ViewModel

}