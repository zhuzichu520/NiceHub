@file: JvmName("ToolSize")

package com.zhuzichu.android.libs.tool

import android.content.Context
import androidx.annotation.DimenRes

/**
 * 将dp值转换为px值
 *
 * @param context context
 * @param dpValue dp值
 * @return 转化后的px值
 */
fun dp2px(context: Context, dpValue: Float): Int {
    val scale: Float = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

/**
 * 将px值转换为dp值
 *
 * @param context context
 * @param pxValue px值
 * @return 转化后的dp值
 */
fun px2dp(context: Context, pxValue: Float): Int {
    val scale: Float = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

/**
 * 将px值转换为sp值
 *
 * @param context context
 * @param pxValue px值
 * @return 转化后的sp值
 */
fun px2sp(context: Context, pxValue: Float): Int {
    val scale = context.resources.displayMetrics.scaledDensity
    return (pxValue / scale + 0.5f).toInt()
}

/**
 * 将sp值转换为px值
 *
 * @param context context
 * @param spValue sp值
 * @return 转化后的px值
 */
fun sp2px(context: Context, spValue: Float): Int {
    val scale = context.resources.displayMetrics.scaledDensity
    return (spValue * scale + 0.5f).toInt()
}

/**
 * 从资源文件id获取像素值
 *
 * @param context context
 * @param id      dimen文件id  R.dimen.resourceId
 * @return R.dimen.resourceId对应的尺寸具体的像素值
 */
fun getDimension(context: Context, @DimenRes id: Int): Int {
    return context.resources.getDimension(id).toInt()
}