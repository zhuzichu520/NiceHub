package com.zhuzichu.android.nicehub.di

import com.zhuzichu.android.mvvm.di.ActivityScoped
import com.zhuzichu.android.nicehub.ActivityMain
import com.zhuzichu.android.nicehub.ui.account.ActivityAccount
import com.zhuzichu.android.nicehub.ui.account.login.module.ModuleLogin
import com.zhuzichu.android.nicehub.ui.feeds.main.module.ModuleFeeds
import com.zhuzichu.android.nicehub.ui.feeds.main.module.ModuleChildFeeds
import com.zhuzichu.android.nicehub.ui.follower.module.ModuleFollower
import com.zhuzichu.android.nicehub.ui.repo.detail.module.ModuleRepoDetail
import com.zhuzichu.android.nicehub.ui.main.module.ModuleMain
import com.zhuzichu.android.nicehub.ui.profile.module.ModuleProfile
import com.zhuzichu.android.nicehub.ui.repo.file.module.ModuleRepoFile
import com.zhuzichu.android.nicehub.ui.repo.search.module.ModuleRepoSearch
import com.zhuzichu.android.nicehub.ui.setting.animation.module.ModuleAnimation
import com.zhuzichu.android.nicehub.ui.setting.languages.module.ModuleLanguages
import com.zhuzichu.android.nicehub.ui.setting.main.module.ModuleSetting
import com.zhuzichu.android.nicehub.ui.setting.theme.module.ModuleTheme
import com.zhuzichu.android.nicehub.ui.user.module.ModuleUser
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
            ModuleRepoDetail::class,
            ModuleSetting::class,
            ModuleLanguages::class,
            ModuleTheme::class,
            ModuleRepoSearch::class,
            ModuleFollower::class,
            ModuleRepoFile::class,
            ModuleUser::class,
            ModuleAnimation::class
        ]
    )
    internal abstract fun mainActivity(): ActivityMain

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleLogin::class
        ]
    )
    internal abstract fun accountActivity(): ActivityAccount

}