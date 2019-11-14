package com.zhuzichu.android.nicehub.ui.repo.detail.fragment

import android.graphics.Color
import android.view.View
import android.webkit.WebView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentRepoDetailBinding
import com.zhuzichu.android.nicehub.ui.repo.detail.viewmodel.ViewModelRepoDetail
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import kotlinx.android.synthetic.main.fragment_repo_detail.*

class FragmentRepoDetail :
    FragmentAnalyticsBase<FragmentRepoDetailBinding, ViewModelRepoDetail>() {

    private val args: FragmentRepoDetailArgs by navArgs()

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_repo_detail

    override fun initView() {
        super.initView()
        webview.setBackgroundColor(Color.TRANSPARENT)
        webview.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null)
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.showEmptyEvent.observe(this, Observer {
            viewModel.emptyVisibility.value = View.VISIBLE
        })
    }

    override fun initFirstData() {
        viewModel.loadReadme(args.login, args.name)
    }

}