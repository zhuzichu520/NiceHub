package com.zhuzichu.android.nicehub.ui.user.viewmodel

import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseFollowUser
import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseGetFollowStatus
import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseGetUserByLogin
import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseUnFollowerUser
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import javax.inject.Inject

class ViewModelUser @Inject constructor(
    private val useCaseGetUserByLogin: UseCaseGetUserByLogin,
    private val useCaseGetFollowStatus: UseCaseGetFollowStatus,
    private val useCaseFollowUser: UseCaseFollowUser,
    private val useCaseUnFollowerUser: UseCaseUnFollowerUser
) : ViewModelAnalyticsBase()