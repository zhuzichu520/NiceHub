package com.zhuzichu.android.nicehub.ui.repo.file.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.repo.file.fragment.FragmentRepoFile
import com.zhuzichu.android.nicehub.ui.repo.file.viewmodel.ViewModelRepoFile
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleRepoFile {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentSearch(): FragmentRepoFile

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelRepoFile::class)
    abstract fun bindViewModelSearch(viewModel: ViewModelRepoFile): ViewModel

}