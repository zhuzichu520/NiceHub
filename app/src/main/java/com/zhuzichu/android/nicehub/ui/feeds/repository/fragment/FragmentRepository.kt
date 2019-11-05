package com.zhuzichu.android.nicehub.ui.feeds.repository.fragment

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.databinding.FragmentRepositoryBinding
import com.zhuzichu.android.nicehub.ui.feeds.repository.entiy.ArgumentRepository
import com.zhuzichu.android.nicehub.ui.feeds.repository.viewmodel.ViewModelRepository
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import kotlinx.android.synthetic.main.fragment_repository.*

class FragmentRepository :
    FragmentAnalyticsBase<ArgumentRepository, FragmentRepositoryBinding, ViewModelRepository>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_repository

    override fun initView() {
        super.initView()
        viewModel.bean.value = argument.bean
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                viewModel.maskVisibility.value = View.GONE
                viewModel.webviewVisibility.value = View.VISIBLE
            }
        }
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.showEmptyEvent.observe(this, Observer {
            viewModel.emptyVisibility.value = View.VISIBLE
        })
    }

    override fun initFirstData() {
        viewModel.loadReadme()
    }
}