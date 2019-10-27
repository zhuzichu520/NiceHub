package com.zhuzichu.android.nicehub.ui.feeds.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.feeds.main.fragment.FragmentChildFeeds
import com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel.ViewModelFeedsChild
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleChildFeeds {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentFeedsItem(): FragmentChildFeeds

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelFeedsChild::class)
    abstract fun bindViewModelFeedsItem(viewModel: ViewModelFeedsChild): ViewModel

}