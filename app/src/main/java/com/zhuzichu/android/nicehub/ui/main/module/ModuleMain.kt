package com.zhuzichu.android.nicehub.ui.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.main.fragment.FragmentMain
import com.zhuzichu.android.nicehub.ui.main.viewmodel.ViewModelMain
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleMain {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentMain(): FragmentMain

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelMain::class)
    abstract fun bindViewModelMain(viewModel: ViewModelMain): ViewModel

}