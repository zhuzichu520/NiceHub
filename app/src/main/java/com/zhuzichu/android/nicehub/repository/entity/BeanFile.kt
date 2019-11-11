package com.zhuzichu.android.nicehub.repository.entity
import com.google.gson.annotations.SerializedName

data class BeanFile(
    @SerializedName("download_url")
    var downloadUrl: String? = null,
    @SerializedName("git_url")
    var gitUrl: String? = null,
    @SerializedName("html_url")
    var htmlUrl: String? = null,
    @SerializedName("_links")
    var links: Links? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("path")
    var path: String? = null,
    @SerializedName("sha")
    var sha: String? = null,
    @SerializedName("size")
    var size: Int? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("url")
    var url: String? = null
)

