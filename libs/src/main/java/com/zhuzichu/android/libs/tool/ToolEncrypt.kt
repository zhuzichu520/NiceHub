@file: JvmName("ToolEncrypt")

package com.zhuzichu.android.libs.tool

import java.security.MessageDigest


enum class EncryptType(val type: String) {
    ENC_TYPE_MD5("MD5"),
    ENC_TYPE_SHA256("SHA-256")
}

/**
 * 获取加密后字符串
 * 默认使用SHA-256
 *
 * @param value   要加密的字符串
 * @param type 加密类型
 * @return 加密后字符串 16进制32位字符串(小写)
 */
fun getEncryptString(value: String?, type: EncryptType): String {
    if (value.isNullOrEmpty()) {
        return ""
    }

    try {
        val md =
            MessageDigest.getInstance(if (type.type.isNotEmpty()) type.type else EncryptType.ENC_TYPE_SHA256.type)
        md.update(value.toByteArray())
        return bytes2Hex(md.digest())
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

/**
 * bytes转16进制字符串
 *
 * @param bytes bytes数组
 * @return 16进制字符串 (小写)
 */
fun bytes2Hex(bytes: ByteArray): String {
    val sb = StringBuilder()
    var tmp: String

    for (bt in bytes) {
        tmp = Integer.toHexString(bt.toInt() and 0xFF)
        if (tmp.length == 1) {
            sb.append("0")
        }
        sb.append(tmp)
    }
    return sb.toString()
}
