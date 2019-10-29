@file: JvmName("ToolEncode")

package com.zhuzichu.android.libs.tool

import android.util.Base64
import com.zhuzichu.android.libs.Const.CHARSET_UTF_8
import java.net.URLDecoder
import java.net.URLEncoder

/**
 * 使用URL编码 UTF-8
 *
 * @param url url编码前值
 * @return 编码后的值
 */
fun encodeUrl(url: String?): String {
    if (url.isNullOrEmpty()) {
        return ""
    }

    try {
        return URLEncoder.encode(url, CHARSET_UTF_8)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

/**
 * 使用URL解码 默认UTF-8
 *
 * @param url url解码前值
 * @return 解码后的值
 */
fun decodeUrl(url: String?): String {
    if (url.isNullOrEmpty()) {
        return ""
    }

    try {
        return URLDecoder.decode(url, CHARSET_UTF_8)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

/**
 * 获取Base64编码后的值
 * 使用{@link Base64#NO_WRAP}方式省略所有换行符
 *
 * @param value 编码前字符串
 * @return 编码后的值
 */
fun encodeBase64(value: String?): String {
    if (value.isNullOrEmpty()) {
        return ""
    }
    try {
        return String(Base64.encode(value.toByteArray(), Base64.NO_WRAP))
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

/**
 * 使用Base64解码.
 * 使用{@link Base64#NO_WRAP}方式省略所有换行符
 *
 * @param value 解码后字符串
 * @return 解码后的值
 */
fun decodeBase64(value: String?): String {
    if (value.isNullOrEmpty()) {
        return ""
    }
    try {
        return String(Base64.decode(value.toByteArray(), Base64.NO_WRAP))
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return ""
}