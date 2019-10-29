@file: JvmName("ToolDeepLink")

package com.zhuzichu.android.libs.tool

import android.net.Uri
import android.text.TextUtils

/**
 * string转Uri对象
 *
 * @param urlStr 深度链接字符串
 * @return 深度链接的uri
 */
fun strToUri(urlStr: String?): Uri {
    if (!TextUtils.isEmpty(urlStr)) {
        try {
            return Uri.parse(urlStr)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    return Uri.parse("")
}

/**
 * 获取深度链接中的"module"的值
 * 例: jollyhic://goods/10086 ，返回goods
 *
 * @param deepLink 深度链接
 * @return module返回值
 */
fun getDeepLinkModule(deepLink: Uri?): String {
    if (null != deepLink) {
        try {
            return if (null != deepLink.authority) deepLink.authority!! else ""
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    return ""
}

/**
 * 获取深度链接中的"id"的值
 * 例: jollyhic://goods/10086 ，返回10086
 *
 * @param deepLink 深度链接
 * @return id部分返回值
 */
fun getDeepLinkIdVal(deepLink: Uri?): String {
    if (deepLink != null) {
        val idValList = deepLink.pathSegments
        return if (null != idValList && idValList.size > 0) idValList[0] else ""
    }
    return ""
}

/**
 * 查找url中的参数值
 * @param url url字符串
 * @param key 查询参数值
 * @return 如果有该参数则返回值，否则返回空字串
 */
fun queryParamsValue(url: String, key: String): String {
    if (TextUtils.isEmpty(url) || TextUtils.isEmpty(key) || !url.contains("?")) {
        return ""
    }

    val paramsStr = url.substring(url.indexOf("?") + 1)
    if (TextUtils.isEmpty(paramsStr)) {
        return ""
    }

    val paramsArr = paramsStr.split("&").filter { it.isNotEmpty() }
    for (param in paramsArr) {
        val index = param.indexOf("=")
        if (index > 0 && index < param.length - 1) {
            val paramKey = decodeUrl(param.substring(0, index))
            val paramValue = decodeUrl(param.substring(index + 1))
            if (paramKey == key) {
                return paramValue
            }
        }
    }

    return ""
}