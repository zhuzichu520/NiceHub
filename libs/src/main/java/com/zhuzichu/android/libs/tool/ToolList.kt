@file: JvmName("ToolList")

package com.zhuzichu.android.libs.tool

/**
 * desc: List工具类 <br/>
 * time: 2019/6/17 下午7:13 <br/>
 * author: Logan <br/>
 * since V 1.3.0.7 <br/>
 */

/**
 * 集合是否为空（null 或 size==0）。
 *
 * @param sourceList 源集合数据
 * @return true：为空
 */
fun <V> isNullOrEmpty(sourceList: List<V>?): Boolean = sourceList?.isEmpty() ?: true

/**
 * 集合中元素个数是否大于0。
 *
 * @param sourceList 源集合数据
 * @return true：是
 */
fun <V> isNotEmpty(sourceList: List<V>?): Boolean = !isNullOrEmpty(sourceList)

/**
 * 获取集合中元素个数。
 *
 * @param sourceList 源集合数据
 * @return 集合元素个数
 */
fun <V> getSize(sourceList: List<V>?) = sourceList?.size ?: 0

/**
 * 将源集合中的元素 复制 到目标集合中。
 *
 * @param sourceList 源集合数据
 * @param destList   目标集合
 */
fun <V> copy(sourceList: List<V>?, destList: MutableList<V>?) {
    if (isNotEmpty(sourceList) && (destList != null)) {
        destList.addAll(sourceList!!)
    }
}

/**
 * 清除集合中的元素。
 * 注意：通过 [java.util.Arrays.asList] 创建的List不能调用该函数，否则会抛出异常。
 *
 * @param sourceList 源集合数据
 */
fun <V> clear(sourceList: MutableList<V>?) {
    if (isNotEmpty(sourceList)) {
        sourceList?.clear()
    }

}