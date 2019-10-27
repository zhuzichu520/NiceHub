package com.zhuzichu.android.nicehub.di

import com.zhuzichu.android.mvvm.di.ActivityScoped
import com.zhuzichu.android.nicehub.ActivityMain
import com.zhuzichu.android.nicehub.ui.feeds.main.module.ModuleFeeds
import com.zhuzichu.android.nicehub.ui.feeds.main.module.ModuleChildFeeds
import com.zhuzichu.android.nicehub.ui.feeds.repository.module.ModuleRepository
import com.zhuzichu.android.nicehub.ui.main.module.ModuleMain
import com.zhuzichu.android.nicehub.ui.profile.module.ModuleProfile
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleMain::class,
            ModuleFeeds::class,
            ModuleProfile::class,
            ModuleChildFeeds::class,
            ModuleRepository::class
        ]
    )
    internal abstract fun mainActivity(): ActivityMain

}