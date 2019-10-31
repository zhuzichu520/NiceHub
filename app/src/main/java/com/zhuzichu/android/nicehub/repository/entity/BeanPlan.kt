package com.zhuzichu.android.nicehub.repository.entity

import com.google.gson.annotations.SerializedName

data class BeanPlan(
    @SerializedName("collaborators")
    var collaborators: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("private_repos")
    var privateRepos: Int? = null,
    @SerializedName("space")
    var space: Int? = null
)