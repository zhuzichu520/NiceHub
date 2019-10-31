package com.zhuzichu.android.nicehub.ui.setting.languages.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.setting.languages.fragment.FragmentLanguages
import com.zhuzichu.android.nicehub.ui.setting.languages.viewmodel.ViewModelLanguages
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleLanguages {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentLanguages(): FragmentLanguages

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelLanguages::class)
    abstract fun bindViewModelLanguages(viewModel: ViewModelLanguages): ViewModel

}