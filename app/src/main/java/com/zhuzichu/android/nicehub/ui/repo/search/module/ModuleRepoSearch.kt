package com.zhuzichu.android.nicehub.ui.repo.search.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.repo.search.fragment.FragmentRepoSearch
import com.zhuzichu.android.nicehub.ui.repo.search.viewmodel.ViewModelRepoSearch
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleRepoSearch {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentSearch(): FragmentRepoSearch

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelRepoSearch::class)
    abstract fun bindViewModelSearch(viewModel: ViewModelRepoSearch): ViewModel

}