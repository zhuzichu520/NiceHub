@file: JvmName("ToolMap")

package com.zhuzichu.android.libs.tool

import androidx.annotation.NonNull
import androidx.annotation.Nullable

/**
 * Map是否为空（null 或 size==0）。
 *
 * @param map 源map数据
 * @return true：为空
 */
fun <KEY, VALUE> isNullOrEmpty(map: Map<KEY, VALUE>?): Boolean = (map == null || map.isEmpty())

/**
 * map中的元素个数是否大于0。
 *
 * @param map 源map数据
 * @return true：是
 */
fun <KEY, VALUE> isNotEmpty(map: Map<KEY, VALUE>?): Boolean = !isNullOrEmpty(map)

/**
 * 获取Map中元素个数。
 *
 * @param map 源map数据
 * @return 元素个数
 */
fun <KEY, VALUE> getSize(map: Map<KEY, VALUE>?): Int = (map?.size ?: 0)

/**
 * 将源Map中的元素 复制到 目标Map集合中。
 *
 * @param sourceMap 数据源
 * @param destMap   目的源
 */
fun <KEY, VALUE> copy(sourceMap: Map<KEY, VALUE>?, destMap: MutableMap<KEY, VALUE>?) {
    if (isNotEmpty(sourceMap) && destMap != null) {
        sourceMap?.let {
            destMap.putAll(sourceMap)
        }
    }
}

/**
 * 将非null key和value 添加到 map中
 *
 * @param map   源数据
 * @param key   key值
 * @param value value值
 */
fun <KEY, VALUE> putNotNull(map: MutableMap<KEY, VALUE>?, key: KEY?, value: VALUE?) {
    if ((map != null) && (key != null) && (value != null)) {
        map[key] = value
    }
}

/**
 * 清除map中的元素
 *
 * @param map map数据
 */
fun <KEY, VALUE> clear(map: MutableMap<KEY, VALUE>?) {
    if (isNotEmpty(map)) {
        map?.clear()
    }
}

/**
 * 根据keys及values去创建Map
 * @param keys keys值
 * @param values values值
 */
fun <KEY, VALUE> create(@NonNull keys: List<KEY>, @NonNull values: List<VALUE>): Map<KEY, VALUE> {
    val keySize = getSize(keys)
    val valueSize = getSize(values)
    //获取最小值 防止角标越界
    val minSize = if (keySize <= valueSize) keySize else valueSize
    val result = HashMap<KEY, VALUE>(minSize)

    if ((keySize > 0) && (valueSize > 0)) {
        for (i in 0 until minSize) {
            val key = keys[i]
            val value = values[i]
            if (key != null && value != null) result[key] = value
        }
    }

    return result
}

/**
 * 根据keys及values去创建Map
 * @param keys keys值
 * @param values values值
 */
fun <KEY, VALUE> create(@Nullable keys: Array<KEY>?, @Nullable values: Array<VALUE>?): Map<KEY, VALUE> {
    val keySize = keys?.size ?: 0
    val valueSize = values?.size ?: 0
    //获取最小值 防止角标越界
    val minSize = if (keySize <= valueSize) keySize else valueSize
    val result = HashMap<KEY, VALUE>(minSize)

    if ((keySize > 0) && (values != null)) {
        for (i in 0 until minSize) {
            keys?.let {
                val key = keys[i]
                val value = values[i]
                if (key != null && value != null) result[key] = value
            }
        }
    }

    return result
}