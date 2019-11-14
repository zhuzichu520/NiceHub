package com.zhuzichu.android.nicehub.repository.entity

import com.google.gson.annotations.SerializedName

data class BeanApp(
    @SerializedName("client_id")
    var clientId: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null
)