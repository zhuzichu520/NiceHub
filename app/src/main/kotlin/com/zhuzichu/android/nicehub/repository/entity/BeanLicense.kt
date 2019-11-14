package com.zhuzichu.android.nicehub.repository.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BeanLicense(
    @SerializedName("key")
    var key: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("spdx_id")
    var spdxId: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("node_id")
    var nodeId: String? = null
) : Parcelable