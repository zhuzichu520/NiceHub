package com.zhuzichu.android.nicehub.ui.follower.fragment

import androidx.navigation.fragment.navArgs
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentFollowerBinding
import com.zhuzichu.android.nicehub.ui.follower.viewmodel.ViewModelFollower
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.toStringByResId
import kotlinx.android.synthetic.main.fragment_follower.*

class FragmentFollower : FragmentAnalyticsBase<FragmentFollowerBinding, ViewModelFollower>() {

    companion object {
        const val FOLLOWER_FOLLOWERS = "followers"
        const val FOLLOWER_FOLLOWING = "following"
    }

    val args: FragmentFollowerArgs by navArgs()

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_follower

    override fun initView() {
        super.initView()
        toolbar.titleText = if (args.follower == FOLLOWER_FOLLOWERS)
            R.string.followers.toStringByResId()
        else
            R.string.following.toStringByResId()
        viewModel.follower = args.follower
    }

}