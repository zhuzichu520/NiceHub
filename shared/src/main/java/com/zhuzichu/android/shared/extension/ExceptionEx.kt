package com.zhuzichu.android.shared.extension

import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.text.ParseException


const val UNAUTHORIZED = 401
private const val FORBIDDEN = 403
const val NOT_FOUND = 404
private const val REQUEST_TIMEOUT = 408
private const val INTERNAL_SERVER_ERROR = 500
private const val SERVICE_UNAVAILABLE = 503

/**
 * 未知错误
 */
private const val UNKNOWN = 1000
/**
 * 解析错误
 */
private const val PARSE_ERROR = 1001
/**
 * 网络错误
 */
private const val NETWORD_ERROR = 1002
/**
 * 协议出错
 */
private const val HTTP_ERROR = 1003

/**
 * 证书出错
 */
private const val SSL_ERROR = 1005

/**
 * 连接超时
 */
private const val TIMEOUT_ERROR = 1006

class ResponseThrowable(throwable: Throwable, var code: Int) : Exception(throwable) {
    override var message: String? = null
}

fun Throwable.handleException(): ResponseThrowable {
    val ex: ResponseThrowable
    if (this is HttpException) {
        ex = ResponseThrowable(this, UNKNOWN)
        when (this.code()) {
            UNAUTHORIZED -> ex.message = "操作未授权"
            FORBIDDEN -> ex.message = "请求被拒绝"
            NOT_FOUND -> ex.message = "资源不存在"
            REQUEST_TIMEOUT -> ex.message = "服务器执行超时"
            INTERNAL_SERVER_ERROR -> ex.message = "服务器内部错误"
            SERVICE_UNAVAILABLE -> ex.message = "服务器不可用"
            else -> ex.message = "网络错误"
        }
        ex.code = this.code()
        return ex
    } else if (this is JsonParseException
        || this is JSONException
        || this is ParseException || this is MalformedJsonException
    ) {
        ex = ResponseThrowable(this, PARSE_ERROR)
        ex.message = "解析错误"
        return ex
    } else if (this is ConnectException) {
        ex = ResponseThrowable(this, NETWORD_ERROR)
        ex.message = "连接失败"
        return ex
    } else if (this is javax.net.ssl.SSLException) {
        ex = ResponseThrowable(this, SSL_ERROR)
        ex.message = "证书验证失败"
        return ex
    } else if (this is java.net.SocketTimeoutException) {
        ex = ResponseThrowable(this, TIMEOUT_ERROR)
        ex.message = "连接超时"
        return ex
    } else if (this is java.net.UnknownHostException) {
        ex = ResponseThrowable(this, TIMEOUT_ERROR)
        ex.message = "主机地址未知"
        return ex
    } else if (this is ResponseThrowable) {
        ex = this
        return ex
    } else {
        ex = ResponseThrowable(this, UNKNOWN)
        ex.message = "未知错误"
        return ex
    }
}