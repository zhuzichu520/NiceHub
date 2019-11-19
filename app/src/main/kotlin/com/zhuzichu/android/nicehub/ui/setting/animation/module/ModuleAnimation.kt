package com.zhuzichu.android.nicehub.ui.setting.animation.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.setting.animation.fragment.FragmentAnimation
import com.zhuzichu.android.nicehub.ui.setting.animation.viewmodel.ViewModelAnimation
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleAnimation {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentAnimation(): FragmentAnimation

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelAnimation::class)
    abstract fun bindViewModelAnimation(viewModel: ViewModelAnimation): ViewModel

}