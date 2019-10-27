package com.zhuzichu.android.nicehub.ui.profile.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.viewmodel.ViewModelItemSectionIcon
import com.zhuzichu.android.nicehub.viewmodel.ViewModelItemSectionLine
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelProfile @Inject constructor() : ViewModelAnalyticsBase() {

    companion object {
        const val MAIN_SECTION_SETTING = 0
    }

    private val onClickSection: (Int) -> Unit = {
        when (it) {
            MAIN_SECTION_SETTING -> {
                startFragment(
                    R.id.action_fragmentMain_to_fragmentRepository
                )
            }
            else -> {
            }
        }
    }

    val items = MutableLiveData<List<Any>>().also {
        it.value = listOf(
            ViewModelItemSectionLine(this),
            ViewModelItemSectionIcon(
                this,
                MAIN_SECTION_SETTING,
                R.drawable.ic_setting,
                R.string.setting,
                onClickSection
            )
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ViewModelItemSectionIcon>(BR.item, R.layout.item_section_icon)
        map<ViewModelItemSectionLine>(BR.item, R.layout.item_section_line)
    }
}