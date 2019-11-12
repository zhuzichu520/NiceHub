package com.zhuzichu.android.nicehub.ui.user.viewmodel

import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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
import com.zhuzichu.android.shared.extension.ParseDateFormat
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import java.util.ArrayList
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

    private val lists = MutableLiveData<List<Any>>()

    private val itemViewModelUserFollow = ItemViewModelUserFollow(this)

    val items: LiveData<List<Any>> =
        Transformations.map<List<Any>, List<Any>>(lists) { input ->
            val list = ArrayList<Any>(input.size + 1)
            list.add(itemViewModelUserFollow)
            list.addAll(input)
            list
        }

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
                lists.value = initUserTextList(it)
            }, {
                handleThrowable(it)
            })
    }

    fun updateFollowStatus() {
        useCaseGetFollowStatus.execute(login.value.toString())
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({

            }, {
                handleThrowable(it)
            })
    }

    private fun initUserTextList(it: BeanUser): List<Any> {
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