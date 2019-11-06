package com.zhuzichu.android.nicehub.ui.follower.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.follower.fragment.FragmentFollower
import com.zhuzichu.android.nicehub.ui.follower.viewmodel.ViewModelFollower
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleFollower {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentFollower(): FragmentFollower

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelFollower::class)
    abstract fun bindViewModelFollower(viewModel: ViewModelFollower): ViewModel

}