package com.zhuzichu.android.nicehub.ui.repositories.detail.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.repositories.detail.fragment.FragmentRepository
import com.zhuzichu.android.nicehub.ui.repositories.detail.viewmodel.ViewModelRepository
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleRepository {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentRepository(): FragmentRepository

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelRepository::class)
    abstract fun bindViewModelFeeds(viewModel: ViewModelRepository): ViewModel

}