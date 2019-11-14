package com.zhuzichu.android.nicehub.ui.profile.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.profile.fragment.FragmentProfile
import com.zhuzichu.android.nicehub.ui.profile.viewmodel.ViewModelProfile
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleProfile {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentProfile(): FragmentProfile

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelProfile::class)
    abstract fun bindViewModelProfile(viewModel: ViewModelProfile): ViewModel

}