package com.zhuzichu.android.nicehub.ui.setting.theme.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.setting.theme.fragment.FragmentTheme
import com.zhuzichu.android.nicehub.ui.setting.theme.viewmodel.ViewModelTheme

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleTheme {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentTheme(): FragmentTheme

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelTheme::class)
    abstract fun bindViewModelTheme(viewModel: ViewModelTheme): ViewModel

}