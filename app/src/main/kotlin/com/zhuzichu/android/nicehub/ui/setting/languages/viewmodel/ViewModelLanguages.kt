package com.zhuzichu.android.nicehub.ui.setting.languages.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.storage.GlobalStorage
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelLanguages @Inject constructor(
    private val globalStorage: GlobalStorage
) : ViewModelAnalyticsBase() {

    companion object {
        private const val LOCAL_EN = "en"
        private const val LOCAL_ZH = "zh"
        private const val LOCAL_AR = "ar"
    }

    val languagesChangeEvent = SingleLiveEvent<String>()

    val items = MutableLiveData<List<Any>>()

    fun loadSectionLable() {
        val locale = globalStorage.locale
        items.value = listOf(
            ItemViewModelLanguage(this, "English", LOCAL_EN, locale == LOCAL_EN),
            ItemViewModelLanguage(this, "中文", LOCAL_ZH, locale == LOCAL_ZH),
            ItemViewModelLanguage(this, "عربى", LOCAL_AR, locale == LOCAL_AR)
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelLanguage>(BR.item, R.layout.item_language)
    }

}