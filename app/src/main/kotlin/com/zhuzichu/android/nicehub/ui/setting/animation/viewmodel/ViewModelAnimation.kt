package com.zhuzichu.android.nicehub.ui.setting.animation.viewmodel

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelAnimation @Inject constructor() : ViewModelAnalyticsBase() {

    val items = MutableLiveData<List<Any>>()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelAnimation>(BR.item, R.layout.item_animation)
    }


    fun updateData() {
        items.value = listOf(
            ItemViewModelAnimation(
                this,
                R.string.fade,
                false
            ),
            ItemViewModelAnimation(
                this,
                R.string.slide,
                false
            )
        )
    }

}