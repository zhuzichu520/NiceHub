package com.zhuzichu.android.nicehub.ui.repo.search.entity

data class ParamterSearchRepo(
    val q: String,
    val sort: String,
    val order: String,
    val page: Int,
    val pageSize: Int
)