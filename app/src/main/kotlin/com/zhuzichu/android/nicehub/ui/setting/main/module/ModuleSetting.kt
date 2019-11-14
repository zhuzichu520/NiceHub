package com.zhuzichu.android.nicehub.ui.setting.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.setting.main.fragment.FragmentSetting
import com.zhuzichu.android.nicehub.ui.setting.main.viewmodel.ViewModelSetting
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleSetting {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentSetting(): FragmentSetting

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelSetting::class)
    abstract fun bindViewModelSetting(viewModel: ViewModelSetting): ViewModel

}