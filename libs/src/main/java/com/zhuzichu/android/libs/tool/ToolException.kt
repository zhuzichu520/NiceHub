@file: JvmName("ToolException")

package com.zhuzichu.android.libs.tool

import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.libs.lambda.BiConsumer2
import java.io.PrintWriter
import java.io.StringWriter

/**
 *
 * 是否开启debug模式。true：是。
 */
private var mIsDebugEnable = false

/**
 * log 日志消费器
 */
private var mLogConsumer: BiConsumer2<String, String>? = null

/**
 * 初始化数据
 *
 * @param isDebugEnable 开启debug模式，true：是 <br/>
 * @param logConsumer   log消费器，第一个范性String -> tag; 第二个String -> error msg，Demo如下：<br/>
 *                      init(true, (tag, errorMsg) -> {
 *                          ToolLog.e(tag, errorMsg);
 *                      });
 */
fun init(isDebugEnable: Boolean, logConsumer: BiConsumer2<String, String>?) {
    mIsDebugEnable = isDebugEnable
    mLogConsumer = logConsumer
}

/**
 * 错误打印。
 *
 * @param logTagCls      log tag class
 * @param customErrorMsg 自定义异常信息
 * @param e              异常Exception
 */
@JvmOverloads
fun printStackTrace(logTagCls: Class<*>?, customErrorMsg: String = "", e: Throwable?) {
    printStackTrace(logTagCls?.simpleName ?: "", customErrorMsg, e)
}

/**
 * 错误打印 (debug模式会主动抛出异常，挂断程序)
 *
 * @param logTag         log tag
 * @param customErrorMsg 自定义异常信息
 * @param e              异常Exception
 */
@JvmOverloads
fun printStackTrace(logTag: String?, customErrorMsg: String = "", e: Throwable?) {
    e?.printStackTrace()
    outputLog(
        logTag,
        "printStackTrace()-> " + ("Msg:" + customErrorMsg + ", Cause:" + getErrLogMsg(e))
    )
    throwExceptionOnUIThread("========== 发生异常，终断程序运行! ==========")
}

/**
 * 抛出异常，不同环境表现不同：<br/>
 * 1，debug环境：立即抛出异常。<br/>
 * 2，正式环境：[mLogConsumer] 行为而定。<br/>
 *
 * @param tag log tag
 * @param msg 错误信息
 */
fun throwException(tag: String?, msg: String?) {
    outputLog(tag, msg)
    throwExceptionOnUIThread(msg)
}

/**
 *
 * 收集Throwable错误日志信息
 *
 * @param e Throwable
 * @return errorMsg
 */
fun getErrLogMsg(e: Throwable?): String {
    if (e == null) {
        return ""
    }

    val writer = StringWriter()
    val printWriter = PrintWriter(writer)
    e.printStackTrace(printWriter)
    var cause: Throwable? = e.cause

    while (cause != null) {
        cause.printStackTrace(printWriter)
        cause = cause.cause
    }

    val crashInfo = writer.toString()
    runCatching {
        printWriter.close()
        writer.close()
    }.onFailure { exception -> exception.printStackTrace() }

    return crashInfo
}

/**
 * 在主线程抛出异常(仅在debug模式下)
 *
 * @param msg 错误信息
 */
fun throwExceptionOnUIThread(msg: String?) {
    if (mIsDebugEnable) {
        if (isUIThread()) {
            throw IllegalStateException(if (msg.isNullOrEmpty()) "" else msg)
        } else {
            MainHandler.post(Runnable { throw IllegalAccessError(if (msg.isNullOrEmpty()) "" else msg) })
        }
    }
}

/**
 * log 输出
 */
fun outputLog(tag: String?, msg: String?) {
    mLogConsumer?.accept(
        if (tag.isNullOrEmpty()) "ToolException" else tag
        , if (msg.isNullOrEmpty()) "" else msg
    )
}