package com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.uber.autodispose.android.lifecycle.autoDispose
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.ui.feeds.main.domain.UseCaseGetHotRepos
import com.zhuzichu.android.nicehub.ui.feeds.main.entity.ParamterGetHotRepos
import com.zhuzichu.android.shared.base.BaseDataSource
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelFeedsChild @Inject constructor(
    private val useCaseGetHotRepos: UseCaseGetHotRepos
) : ViewModelAnalyticsBase() {

    val items = MutableLiveData<List<Any>>()

    val pagedList: LiveData<PagedList<Any>> =
        LivePagedListBuilder(object : DataSource.Factory<Int, Any>() {
            override fun create(): DataSource<Int, Any> {
                return object : BaseDataSource<Any>() {
                    override fun loadInitialData(
                        params: LoadInitialParams<Int>,
                        callback: LoadInitialCallback<Int, Any>
                    ) {
                        useCaseGetHotRepos.execute(ParamterGetHotRepos("Kotlin", 1))
                         .subscribe({
                                submitInitialData(it.items!!.map { item ->
                                    ItemViewModelRepository(this@ViewModelFeedsChild, item)
                                }, params, callback)
                             toast("执行了")
                            }, {
                                handleThrowable(it)
                            })

                    }

                    override fun loadAditionalData(
                        params: LoadParams<Int>,
                        callback: LoadCallback<Int, Any>
                    ) {

                    }
                }
            }
        }, PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(19).build()).build()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelRepository>(BR.item, R.layout.item_repository)
    }

    fun loadData(q: String) {
//        useCaseGetHotRepos.execute(ParamterGetHotRepos(q, 1))
//            .autoLoading(this)
//            .autoDispose(lifecycleOwner).subscribe({
//                items.value = it.items?.map { item ->
//                    ItemViewModelRepository(
//                        this,
//                        item
//                    )
//                }
//            }, {
//                handleThrowable(it)
//            })
    }
}