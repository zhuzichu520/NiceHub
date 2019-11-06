package com.zhuzichu.android.nicehub.ui.feeds.main.fragment

import androidx.core.os.bundleOf
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentProfileBinding
import com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel.ViewModelFeeds
import com.zhuzichu.android.shared.base.DefaultStringFragmentPagerAdapter
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.toArrayByResId
import kotlinx.android.synthetic.main.fragment_feeds.*

class FragmentFeeds :
    FragmentAnalyticsBase<FragmentProfileBinding, ViewModelFeeds>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_feeds

    override fun initView() {
        val titles = R.array.languages.toArrayByResId()
        val fragments = titles.map {
            FragmentChildFeeds().apply {
                arguments = bundleOf(::title.name to it)
            }
        }
        pager.adapter =
            DefaultStringFragmentPagerAdapter(childFragmentManager, fragments, titles.toList())
        tab.setupWithViewPager(pager)
    }

}
