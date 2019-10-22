package com.zhuzichu.android.nicehub.di

import com.zhuzichu.android.mvvm.di.ActivityScoped
import com.zhuzichu.android.nicehub.ActivityMain
import com.zhuzichu.android.nicehub.ui.feeds.module.ModuleFeeds
import com.zhuzichu.android.nicehub.ui.feeds.module.ModuleFeedsItem
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
            ModuleFeedsItem::class
        ]
    )
    internal abstract fun mainActivity(): ActivityMain

}