package com.zhuzichu.android.nicehub.ui.user.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.nicehub.ui.user.fragment.FragmentUser
import com.zhuzichu.android.nicehub.ui.user.viewmodel.ViewModelUser
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleUser {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentUser(): FragmentUser

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelUser::class)
    abstract fun bindViewModelUser(viewModel: ViewModelUser): ViewModel

}