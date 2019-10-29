@file: JvmName("ToolNumber")

package com.zhuzichu.android.libs.tool

import androidx.annotation.IntRange
import java.text.DecimalFormat

/**
 * 校验字符串是否都是数字 <br/>
 * 示例：<br/>
 * true: 0  11 1234545679 <br/>
 * false: -2  -1.1  0.0  1.3  １２３(全角的) <br/>
 *
 * @param numberValue 需要校验的数字字符串
 * @return true:是数字
 */
fun isDigit(numberValue: String?): Boolean? =
    if ((numberValue?.length ?: 0) == 0) false else numberValue?.matches("[0-9]+".toRegex())


/**
 * 校验字符串是否是整数 ("整数"指的整数类型，非小数点类型。即：包括负数、Long数据类型均返回true)
 * 示例：
 * true: -12  0  15
 * false: -1.1  0.0  1.3  １２３(全角的)
 *
 * @param numberValue 需要校验的数值字符串
 * @return true:是数值
 */
fun isInteger(numberValue: String?): Boolean? {
    return if (numberValue.isNullOrEmpty()) {
        false
    } else {
        numberValue.matches("-[0-9]+|[0-9]+".toRegex())
    }
}


/**
 * 校验字符串是否是数值 (包含负数与浮点数) <br/>
 * <p>
 * 示例：<br/>
 * true: -12  -12.0  -12.056  12  12.0  12.056 <br/>
 * false: .  1.  1sr  -   12.  -12.  １２３(全角的) <br/>
 *
 * @param numberValue 需要校验的字符串
 * @return true：是数值
 */
fun isNumeric(numberValue: String?): Boolean {
    if (numberValue.isNullOrEmpty() || !numberValue.matches("-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?".toRegex())) {
        return false
    }
    return true
}


/**
 * 强转成int基本类型值，默认0 (譬如：数据类型不匹配、转换异常) <br/>
 * <p>
 * 示例：<br/>
 * null、""、1abc、ab2、１２３４(全角) -> 0 <br/>
 * 11、11.13、"11.99" -> 11 <br/>
 * -1、-1.13、"-1.99" -> -1 <br/>
 *
 * @param intValue Integer、Number或String类型的int值
 * @return 强转后的int值
 */
fun toInt(intValue: Any?): Int {
    when (intValue) {
        is Int -> return intValue
        is Number -> return intValue.toInt()
        is String -> {
            try {
                return intValue.toDouble().toInt()
            } catch (ignored: NumberFormatException) {
                ignored.printStackTrace()
            }
        }
    }
    return 0
}

/**
 * 强转成long基本类型值，默认0 (譬如：数据类型不匹配、转换异常) <br/>
 * <p>
 * 示例：<br/>
 * null、""、1abc、ab2、１２３４(全角) -> 0 <br/>
 * 11、11.13、"11.99" -> 11 <br/>
 * -1、-1.13、"-1.99" -> -1 <br/>
 *
 * @param longValue Long、String类型的Long值
 * @return 强转后的long值
 */
fun toLong(longValue: Any?): Long {
    when (longValue) {
        is Long -> return longValue
        is Number -> return longValue.toLong()
        is String -> {
            try {
                return longValue.toDouble().toLong()
            } catch (ignored: NumberFormatException) {
                ignored.printStackTrace()
            }
        }
    }
    return 0
}

/**
 * 强转成short基本类型值，默认0 (譬如：数据类型不匹配、转换异常)
 *
 *
 * short类型：最小值是 -32768，最大值是32767。超出范围转换后的数值不准确。
 * 如果不确定数据范围，请使用 [.toInt]、 [.toLong]。</br>
 *
 *
 * 示例： </br>
 * null、""、1abc、ab2、１２３４(全角) -> 0 </br>
 * 55、55.13、"55.99" -> 55 </br>
 * -5、-5.13、"-5.99" -> -5 </br>
 *
 * @param shortValue Short、Number、String类型的short值
 * @return 强转后的short值
 */
fun toShort(shortValue: Any?): Short {
    when (shortValue) {
        is Short -> return shortValue
        is Number -> return shortValue.toShort()
        is String -> {
            try {
                return shortValue.toDouble().toShort()
            } catch (ignored: NumberFormatException) {
                ignored.printStackTrace()
            }
        }
    }
    return 0
}

/**
 * 强转成double基本类型值，默认0.0 (譬如：数据类型不匹配、转换异常) </br>
 *
 * @param decFormat   浮点数格式化器
 * @param doubleValue 浮点值
 * @return 格式化后的double值
 */
fun toDouble(decFormat: DecimalFormat, doubleValue: Any?): Double =
    java.lang.Double.parseDouble(decFormat.format(toDouble(doubleValue)))

/**
 * 强转成double基本类型值，不截取、不会四舍五入，默认0.0 (譬如：数据类型不匹配、转换异常) <br></br>
 * 可能存在(1.0/3 = 0.333333333333333)未截取情况，使用 [.toDouble] 可处理。<br></br>
 *
 *
 * 示例：<br></br>
 * null、""、1.1abc、ab2.2、１２３４(全角) -> 0.0 <br></br>
 * 55.66、"55.66" -> 55.66 <br></br>
 * -5.4999、"-5.4999" -> -5.4999 <br></br>
 *
 * @param doubleValue 浮点值
 * @return 转换后的浮点值
 */
fun toDouble(doubleValue: Any?): Double {
    when (doubleValue) {
        is Double -> return doubleValue
        is Number -> return doubleValue.toDouble()
        is String -> {
            try {
                return doubleValue.toDouble()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    return 0.0
}

/**
 * 获取 1 到 x 之间随机数 (包括1、X) </br>
 * x最小数字是1，否则会不准确。</br>
 *
 * @param x 随机数最大数
 * @return 随机数
 */
fun getRandom1ToX(@IntRange(from = 1) x: Int): Int {
    var randomValue = (Math.random() * x + 1).toLong()

    if (randomValue > Integer.MAX_VALUE) {
        randomValue = Integer.MAX_VALUE.toLong()
    }

    return randomValue.toInt()
}

/**
 * 强转成float基本类型值，不截取、不会四舍五入，默认0.0f (譬如：数据类型不匹配、转换异常) </br>
 *
 * 示例：</br>
 * null、""、1.1abc、ab2.2、１２３４(全角) -> 0.0 </br>
 * 55.66、"55.66" -> 55.66f </br>
 * -5.4999、"-5.4999" -> -5.4999f </br>
 *
 * @param floatValue 浮点值
 * @return 转换后的浮点值
 */
fun toFloat(floatValue: Any?): Float {
    when (floatValue) {
        is Float -> return floatValue
        is Number -> return floatValue.toFloat()
        is String -> {
            try {
                return floatValue.toFloat()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    return 0.0f
}