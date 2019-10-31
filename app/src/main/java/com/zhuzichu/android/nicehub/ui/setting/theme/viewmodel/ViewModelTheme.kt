package com.zhuzichu.android.nicehub.ui.setting.theme.viewmodel

import androidx.appcompat.app.AppCompatDelegate.*
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map

import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelTheme @Inject constructor() : ViewModelAnalyticsBase() {

    val items = MutableLiveData<List<Any>>()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelTheme>(BR.item, R.layout.item_theme)
    }

    val themeChangeEvent = SingleLiveEvent<Int>()

    fun loadSectionLable() {
        items.value = listOf(
            ItemViewModelTheme(
                this,
                R.string.settings_theme_light,
                MODE_NIGHT_NO,
                isCurrentModel(MODE_NIGHT_NO)
            ),
            ItemViewModelTheme(
                this,
                R.string.settings_theme_dark,
                MODE_NIGHT_YES,
                isCurrentModel(MODE_NIGHT_YES)
            ),
            ItemViewModelTheme(
                this,
                R.string.settings_theme_system,
                MODE_NIGHT_FOLLOW_SYSTEM,
                isCurrentModel(MODE_NIGHT_FOLLOW_SYSTEM)
            ),
            ItemViewModelTheme(
                this,
                R.string.settings_theme_battery,
                MODE_NIGHT_AUTO_BATTERY,
                isCurrentModel(MODE_NIGHT_AUTO_BATTERY)
            )
        )
    }

    private fun isCurrentModel(mode: Int): Boolean = mode == getDefaultNightMode()

}