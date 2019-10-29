@file: JvmName("ToolMath")

package com.zhuzichu.android.libs.tool

import androidx.annotation.IntRange
import java.math.BigDecimal

/**
 * 加法
 *
 * @param d1 数字
 * @param d2 数字
 * @return 数字
 */
fun doubleAdd(d1: Double, d2: Double) =
    BigDecimal.valueOf(d1).add(BigDecimal.valueOf(d2)).toDouble()

/**
 * 减法
 *
 * @param d1 数字
 * @param d2 数字
 * @return 数字
 */
fun doubleSubtract(d1: Double, d2: Double) =
    BigDecimal.valueOf(d1).subtract(BigDecimal.valueOf(d2)).toDouble()

/**
 * 乘法运算
 *
 * @param d1 数字
 * @param d2 数字
 * @return 数字
 */
fun doubleMul(d1: Double, d2: Double) =
    if (d1 == 0.0 || d2 == 0.0) 0.0 else BigDecimal.valueOf(d1).multiply(BigDecimal.valueOf(d2)).toDouble()

/**
 * 除法运算 (四舍五入)，同时根据参数保留小数点位数
 *
 * @param d1    数字
 * @param d2    数字
 * @param scale 小数点位数
 * @return 计算结果
 */
fun doubleDiv(d1: Double, d2: Double, @IntRange(from = 0) scale: Int) =
    if (d2 == 0.0) 0.0 else BigDecimal.valueOf(d1).divide(
        BigDecimal.valueOf(d2),
        scale,
        BigDecimal.ROUND_HALF_UP
    )
        .toDouble()

/**
 * 截取小数点位数，同时四舍五入
 *
 * @param d     数值
 * @param scale 小数点位数
 *              保留小数点个数
 * @return 四舍五入的结果
 */
fun doubleRound(d: Double, @IntRange(from = 0) scale: Int) = BigDecimal.valueOf(d)
    .setScale(scale, BigDecimal.ROUND_HALF_UP).toDouble()

/**
 * 精确的比较方法: >
 *
 * @param d1 数字
 * @param d2 数字
 * @return true表示 d1 > d2
 */
fun greatThan(d1: Double, d2: Double) = BigDecimal.valueOf(d1) > BigDecimal.valueOf(d2)

/**
 * 精确的比较方法: =
 *
 * @param d1 数字
 * @param d2 数字
 * @return true表示 d1 == d2
 */
fun equalsThan(d1: Double, d2: Double) = BigDecimal.valueOf(d1) == BigDecimal.valueOf(d2)

/**
 * 精确的比较方法: <
 *
 * @param d1 数字
 * @param d2 数字
 * @return true表示 d1 < d2
 */
fun lessThan(d1: Double, d2: Double) = BigDecimal.valueOf(d1) < BigDecimal.valueOf(d2)

/**
 * 精确的比较方法: >=
 *
 * @param d1 数字
 * @param d2 数字
 * @return true表示 d1 >= d2
 */
fun greatEquals(d1: Double, d2: Double) = equalsThan(d1, d2) || greatThan(d1, d2)

/**
 * 精确的比较方法: <=
 *
 * @param d1 数字
 * @param d2 数字
 * @return true表示 d1 <= d2
 */
fun lessEquals(d1: Double, d2: Double) = equalsThan(d1, d2) || lessThan(d1, d2)