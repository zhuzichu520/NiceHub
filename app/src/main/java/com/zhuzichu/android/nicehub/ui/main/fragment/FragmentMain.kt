package com.zhuzichu.android.nicehub.ui.main.fragment

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentMainBinding
import com.zhuzichu.android.nicehub.ui.feeds.main.fragment.FragmentFeeds
import com.zhuzichu.android.nicehub.ui.main.viewmodel.ViewModelMain
import com.zhuzichu.android.nicehub.ui.profile.fragment.FragmentProfile
import com.zhuzichu.android.shared.base.DefaultIntFragmentPagerAdapter
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.setupWithViewPager
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : FragmentAnalyticsBase<FragmentMainBinding, ViewModelMain>() {

    private val waitTime = 2000L
    private var touchTime: Long = 0

    override fun setLayoutId(): Int = R.layout.fragment_main

    override fun bindVariableId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        val fragments = listOf<Fragment>(
            FragmentFeeds(),
            FragmentProfile()
        )

        val titles = listOf(
            R.string.feeds,
            R.string.profile
        )

        content.offscreenPageLimit = fragments.size
        content.adapter = DefaultIntFragmentPagerAdapter(childFragmentManager, fragments, titles)
        bottom.setupWithViewPager(content)
        initBackListener()
    }

    private fun initBackListener() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (System.currentTimeMillis() - touchTime < waitTime) {
                //退出app并清除任务栈
                requireActivity().finishAndRemoveTask()
            } else {
                touchTime = System.currentTimeMillis()
                viewModel.toast(R.string.press_again_to_exit)
            }
        }
    }

}