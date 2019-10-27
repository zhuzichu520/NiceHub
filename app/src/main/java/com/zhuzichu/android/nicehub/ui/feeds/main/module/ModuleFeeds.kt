package com.zhuzichu.android.nicehub.ui.feeds.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.feeds.main.fragment.FragmentFeeds
import com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel.ViewModelFeeds
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleFeeds {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentFeeds(): FragmentFeeds

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelFeeds::class)
    abstract fun bindViewModelFeeds(viewModel: ViewModelFeeds): ViewModel

}