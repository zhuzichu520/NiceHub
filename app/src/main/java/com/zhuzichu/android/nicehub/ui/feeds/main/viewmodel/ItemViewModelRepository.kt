package com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.extension.toLanguageCircleDrawable
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.ui.feeds.repository.entiy.ArgumentRepository
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelRepository(
    viewModel: BaseViewModel,
    bean: BeanRepository
) : ItemViewModelAnalyticsBase(viewModel) {

    val id = bean.id

    val name = MutableLiveData(bean.name)

    val description = MutableLiveData(bean.description)

    val starsCount = MutableLiveData(bean.stargazersCount.toString())

    val forksCount = MutableLiveData(bean.forksCount.toString())

    val language = MutableLiveData(bean.language)

    val languageIcon = MutableLiveData(
        bean.language.toLanguageCircleDrawable()
    )

    val onClickItem = BindingCommand<Any>({
        startFragment(
            R.id.action_fragmentMain_to_fragmentRepository,
            ArgumentRepository(bean)
        )
    })

}