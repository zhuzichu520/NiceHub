@file: JvmName("ToolDate")

package com.zhuzichu.android.libs.tool

import java.text.SimpleDateFormat
import java.util.*

/**
 * 获取指定时期模版的时间格式
 * 默认指定{@link Locale#US}统一输出为阿拉伯数字
 *
 * @param timeStamp 时间戳 单位秒数
 * @param pattern   例如yyyy/MM/dd 参考 {https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html}
 * @return 格式字符串如：2016/12/13 日期或pattern有问题可能返回""
 */
fun getFormatDate(timeStamp: Long, pattern: String): String {
    return getFormatDate(Date(timeStamp * 1000), pattern, Locale.US)
}

/**
 * 获取指定时期模版的时间格式
 *
 * @param date    日期
 * @param pattern 例如yyyy/MM/dd 参考 {https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html}
 * @param locale  区域 如果要对输出日期本地化处理可以指定locale为 [Locale.getDefault]
 * @return 格式字符串如：2016/12/13 日期或pattern有问题可能返回""
 */
@JvmOverloads
fun getFormatDate(date: Date, pattern: String, locale: Locale = Locale.US): String {
    return runCatching {
        SimpleDateFormat(pattern, locale).format(date)
    }.getOrDefault("")
}

/**
 * 根据秒数获取时分秒
 *
 * @param seconds 秒数
 * @return 返回大小为3的int数组 int[0]-时 int[1]-分 int[2]-秒
 */
fun getHourMinSecond(seconds: Long): IntArray {
    val secondsPerHour = 60 * 60
    val minutesRemain = seconds % secondsPerHour
    return intArrayOf(
        (seconds / secondsPerHour).toInt(),// 时
        (minutesRemain / 60).toInt(),// 分
        (minutesRemain % 60).toInt()//秒
    )
}

/**
 * 根据秒数获取日时分秒
 *
 * @param seconds 秒数
 * @return 返回大小为4的int数组 int[0]-天 int[1]-时 int[2]-分 int[3]-秒
 */
fun getDayHourMinSecond(seconds: Long): IntArray {
    val secondsPerDay = 60 * 60 * 24

    val timeDay = (seconds / secondsPerDay).toInt()// 日
    val srcArr = getHourMinSecond(seconds % secondsPerDay)
    val timeArray = IntArray(4)
    timeArray[0] = timeDay
    System.arraycopy(srcArr, 0, timeArray, 1, 3)
    return timeArray
}

/**
 * 判断两个时间戳是否处于同一天
 *
 * @param timeMillis1 之前的时间 单位为毫秒
 * @param timeMillis2 之后的时间 单位为毫秒
 * @return true:是
 */
fun isSameDay(timeMillis1: Long, timeMillis2: Long): Boolean {
    fun timeMillisToCalendar(timeMillis: Long): Calendar = Calendar.getInstance().apply {
        time = Date(timeMillis)
    }

    val calendar1 = timeMillisToCalendar(timeMillis1)
    val calendar2 = timeMillisToCalendar(timeMillis2)

    fun isSame(field: Int): Boolean {
        return calendar1.get(field) == calendar2.get(field)
    }

    return isSame(Calendar.YEAR) &&
            isSame(Calendar.MONTH) &&
            isSame(Calendar.DAY_OF_MONTH)
}