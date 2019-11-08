package com.zhuzichu.android.nicehub.ui.repo.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.extension.toLanguageCircleDrawable
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.ui.repo.search.fragment.FragmentRepoSearchDirections
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelRepoSearch(
    viewModel: BaseViewModel,
    bean: BeanRepository
) : ItemViewModelAnalyticsBase(viewModel) {

    val id = bean.id

    val name = MutableLiveData(bean.fullName)

    val avatarUrl = MutableLiveData(bean.owner?.avatarUrl)

    val starsCount = MutableLiveData(bean.stargazersCount.toString())

    val forksCount = MutableLiveData(bean.forksCount.toString())

    val language = MutableLiveData(bean.language)

    val languageIcon = MutableLiveData(
        bean.language.toLanguageCircleDrawable()
    )

    val onClickItem = BindingCommand<Any>({
        val directions =
            FragmentRepoSearchDirections.actionFragmentReposSearchToFragmentRepoDetail(
                bean.owner?.login ?: "",
                bean.name ?: ""
            )
        startFragment(
            directions
        )
    })
}