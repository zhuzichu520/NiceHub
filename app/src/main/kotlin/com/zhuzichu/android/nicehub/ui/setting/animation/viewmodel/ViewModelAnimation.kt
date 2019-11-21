package com.zhuzichu.android.nicehub.ui.setting.animation.viewmodel

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.storage.GlobalStorage
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

import com.zhuzichu.android.nicehub.R as R2
import com.zhuzichu.android.shared.R as R1

class ViewModelAnimation @Inject constructor(
    val globalStorage: GlobalStorage
) : ViewModelAnalyticsBase() {

    val items = MutableLiveData<List<Any>>()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelAnimation>(BR.item, R2.layout.item_animation)
    }

    fun updateData() {
        items.value = listOf(
            ItemViewModelAnimation(
                this,
                GlobalStorage.ANIMATION_FADE,
                R2.string.fade,
                globalStorage.animation == GlobalStorage.ANIMATION_FADE
            ),
            ItemViewModelAnimation(
                this,
                GlobalStorage.ANIMATION_SLIDE,
                R2.string.slide,
                globalStorage.animation == GlobalStorage.ANIMATION_SLIDE
            )
        )
    }

}