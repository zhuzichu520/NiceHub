package com.zhuzichu.android.nicehub.ui.setting.main.viewmodel


import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.ui.setting.main.viewmodel.ItemViewModelSetting.Companion.LANGUAGES
import com.zhuzichu.android.nicehub.ui.setting.main.viewmodel.ItemViewModelSetting.Companion.THEME
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelSetting @Inject constructor() : ViewModelAnalyticsBase() {

    val items = MutableLiveData<List<Any>>().also {
        it.value = listOf(
            ItemViewModelSetting(
                this,
                LANGUAGES,
                R.string.languages
            ),
            ItemViewModelSetting(
                this,
                THEME,
                R.string.theme
            )
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelSetting>(BR.item, R.layout.item_setting)
    }


}