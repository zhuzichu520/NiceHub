package com.zhuzichu.android.nicehub.ui.account.login.entity

import com.google.gson.annotations.SerializedName
import com.zhuzichu.android.nicehub.BuildConfig

data class ParamterAuthorizations(
    val scopes: List<String> = listOf("user", "repo", "gist", "notifications"),
    val note: String = BuildConfig.APPLICATION_ID,
    val noteUrl: String = BuildConfig.REDIRECT_URL,
    @SerializedName("client_id") val clientId: String = BuildConfig.GITHUB_CLIENT_ID,
    @SerializedName("client_secret") val clientSecret: String = BuildConfig.GITHUB_SECRET
)