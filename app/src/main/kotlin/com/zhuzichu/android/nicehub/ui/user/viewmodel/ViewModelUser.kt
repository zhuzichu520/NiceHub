package com.zhuzichu.android.nicehub.ui.user.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.repository.entity.BeanUser
import com.zhuzichu.android.nicehub.ui.follower.fragment.FragmentFollower
import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseFollowUser
import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseGetFollowStatus
import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseGetUserByLogin
import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseUnFollowerUser
import com.zhuzichu.android.nicehub.ui.user.fragment.FragmentUserDirections
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.*
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelUser @Inject constructor(
    private val useCaseGetUserByLogin: UseCaseGetUserByLogin,
    private val useCaseGetFollowStatus: UseCaseGetFollowStatus,
    private val useCaseFollowUser: UseCaseFollowUser,
    private val useCaseUnFollowerUser: UseCaseUnFollowerUser
) : ViewModelAnalyticsBase() {

    val user = MutableLiveData<BeanUser>()

    val login = MutableLiveData<String>()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelUserText>(BR.item, R.layout.item_user_text)
        map<ItemViewModelUserFollow>(BR.item, R.layout.item_user_follow)
    }

    private val itemViewModelUserFollow = ItemViewModelUserFollow(this)

    val list =
        AsyncDiffObservableList(itemDiffOf<ItemViewModelUserText> { _, _ -> false })

    val items: List<Any> = MergeObservableList<Any>()
        .insertItem(itemViewModelUserFollow)
        .insertList(list)

    val onClickFollowing = BindingCommand<Any>({
        user.value?.let {
            val directions =
                FragmentUserDirections.actionFragmentUserToFragmentFollower(
                    FragmentFollower.FOLLOWER_FOLLOWING,
                    user.value?.login ?: ""
                )
            startFragment(directions)
        }
    })

    val onClickFollowers = BindingCommand<Any>({
        user.value?.let {
            val directions =
                FragmentUserDirections.actionFragmentUserToFragmentFollower(
                    FragmentFollower.FOLLOWER_FOLLOWERS,
                    user.value?.login.toString()
                )
            startFragment(directions)
        }
    })

    fun updateUser() {
        useCaseGetUserByLogin.execute(login.value.toString())
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
                user.value = it
                list.update(initItemList(it))
            }, {
                handleThrowable(it)
            })
    }

    fun updateFollowStatus() {
        useCaseGetFollowStatus.execute(login.value.toString())
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
                toast("触发了")
                itemViewModelUserFollow.showUnFollow()
            }, {
                handleThrowable(it, false) {
                    if (code == NOT_FOUND) {
                        itemViewModelUserFollow.showFollow()
                    } else {
                        toast(this.message)
                    }
                }
            })
    }


    private fun initItemList(it: BeanUser): List<Any> {
        val list = mutableListOf<Any>()
        it.createdAt?.let {
            list.add(
                ItemViewModelUserText(
                    this,
                    R.drawable.ic_clock,
                    ParseDateFormat.getTimeAgo(it).toString()
                )
            )
        }
        it.email?.let {
            list.add(
                ItemViewModelUserText(
                    this,
                    R.drawable.ic_email,
                    it.toString()
                )
            )
        }
        return list
    }
}