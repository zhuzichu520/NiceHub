@file:JvmName("ToolCommon")

package com.zhuzichu.android.libs.tool


/**
 * 类型强转
 *
 */
fun <T> cast(obj: Any?): T {
    return obj as T
}

/**
 * 类型强转
 *
 */
fun <T> Any?.toCast(): T {
    return this as T
}