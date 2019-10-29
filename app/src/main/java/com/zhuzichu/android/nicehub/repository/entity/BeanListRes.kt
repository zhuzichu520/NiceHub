package com.zhuzichu.android.nicehub.repository.entity

import com.google.gson.annotations.SerializedName
import com.zhuzichu.android.shared.fountain.ListResponseWithEntityCount

class BeanListRes<T : Any> : ListResponseWithEntityCount<T> {

    @SerializedName("total_count")
    var totalCount: Int? = null

    @SerializedName("incomplete_results")
    var incompleteResults: Boolean? = null

    @SerializedName("items")
    var items: List<T>? = null

    override fun getElements(): List<T> = items ?: listOf()

    override fun getEntityCount(): Long = (totalCount ?: 0).toLong()
}