package com.zhuzichu.android.nicehub.repository.entity

import com.google.gson.annotations.SerializedName

data class BeanLinks(
    @SerializedName("git")
    var git: String? = null,
    @SerializedName("html")
    var html: String? = null,
    @SerializedName("self")
    var self: String? = null
)