package com.zhuzichu.android.nicehub.ui.repositories.search.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.repositories.search.fragment.FragmentRepositoriesSearch
import com.zhuzichu.android.nicehub.ui.repositories.search.viewmodel.ViewModelRepositoriesSearch
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleRepositoriesSearch {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentSearch(): FragmentRepositoriesSearch

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelRepositoriesSearch::class)
    abstract fun bindViewModelSearch(viewModel: ViewModelRepositoriesSearch): ViewModel

}