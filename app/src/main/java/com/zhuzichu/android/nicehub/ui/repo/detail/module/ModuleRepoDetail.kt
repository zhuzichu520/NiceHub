package com.zhuzichu.android.nicehub.ui.repo.detail.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.repo.detail.fragment.FragmentRepoDetail
import com.zhuzichu.android.nicehub.ui.repo.detail.viewmodel.ViewModelRepoDetail
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleRepoDetail {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentRepository(): FragmentRepoDetail

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelRepoDetail::class)
    abstract fun bindViewModelFeeds(viewModel: ViewModelRepoDetail): ViewModel

}