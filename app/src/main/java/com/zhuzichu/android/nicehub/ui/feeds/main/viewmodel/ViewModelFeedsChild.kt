package com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.android.lifecycle.autoDispose
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.ui.feeds.main.domain.UseCaseGetHotRepos
import com.zhuzichu.android.nicehub.ui.feeds.main.entity.ParamterGetHotRepos
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.extension.itemDiffOf
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelFeedsChild @Inject constructor(
    private val useCaseGetHotRepos: UseCaseGetHotRepos
) : ViewModelAnalyticsBase() {

    val items =  MutableLiveData<List<Any>>()


    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelRepository>(BR.item, R.layout.item_repository)
    }

    fun loadData(q: String) {
        useCaseGetHotRepos.execute(ParamterGetHotRepos(q, 1))
            .autoLoading(this)
            .autoDispose(lifecycleOwner).subscribe({
                items.value= it.items?.map { item ->
                    ItemViewModelRepository(
                        this,
                        item
                    )
                }
            }, {
                handleThrowable(it)
            })
    }
}