package com.zhuzichu.android.nicehub.repository.entity

import com.google.gson.annotations.SerializedName

data class BeanAuthor(
    @SerializedName("app")
    var app: BeanApp? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("fingerprint")
    var fingerprint: Any? = null,
    @SerializedName("hashed_token")
    var hashedToken: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("note")
    var note: String? = null,
    @SerializedName("note_url")
    var noteUrl: Any? = null,
    @SerializedName("scopes")
    var scopes: List<String?>? = null,
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("token_last_eight")
    var tokenLastEight: String? = null,
    @SerializedName("updated_at")
    var updatedAt: String? = null,
    @SerializedName("url")
    var url: String? = null
)
