@file: JvmName("ToolJson")

package com.zhuzichu.android.libs.tool

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject

/**
 * list 转 json
 *
 * @param list 数据源（List集合）
 * @return json字符串
 */
fun list2Json(list: List<*>?): String? {
    return JSON.toJSONString(list)
}

/**
 * map 转 json
 *
 * @param map 数据源（Map集合）
 * @return json字符串
 */
fun map2Json(map: Map<*, *>?): String? {
    return JSONObject.toJSONString(map)
}

/**
 * 数组 转 json
 *
 * @param array 数据源（数组）
 * @return json字符串
 */
fun array2Json(array: Array<*>?): String? {
    return JSON.toJSONString(array)
}

/**
 * 任意对象 转 json
 *
 * @param any 数据源（任意对象）
 * @return json字符串
 */
fun object2Json(any: Any?): String? {
    return JSON.toJSONString(any)
}

/**
 * json 转 List集合
 *
 * @param json  json字符串
 * @param clazz 集合中元素的Class
 * @param T     集合中元素的范型
 * @return List集合
 */
fun <T> json2List(json: String?, clazz: Class<T>): List<T>? {
    return JSON.parseArray(json, clazz)
}

/**
 * json 转 Map集合
 *
 * @param json  json字符串
 * @return Map集合
 */
fun json2Map(json: String?): Map<*, *>? {
    return JSONObject.parseObject(json)
}

/**
 * json 转 任意对象
 *
 * @param json  json字符串
 * @param clazz 集合中元素的Class
 * @param T     集合中元素的范型
 * @return 范型对应的实例
 */
fun <T> json2Object(json: String?, clazz: Class<T>): T? {
    return JSON.parseObject(json, clazz)
}