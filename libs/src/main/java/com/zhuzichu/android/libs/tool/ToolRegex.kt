@file: JvmName("ToolRegex")

package com.zhuzichu.android.libs.tool

import java.util.regex.Pattern

/**
 * 正则表达式：邮箱
 */
const val REGEX_EMAIL =
    "^([a-zA-Z0-9_\\-.|]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$"

/**
 * 验证email是否符合邮箱规则
 *
 * @param email  待验证的邮箱
 * @return       true：符合邮箱规则
 */
fun isEmail(email: String?): Boolean = isNotEmpty(email) && Pattern.matches(REGEX_EMAIL, email)

/**
 * 验证字符串是否满足正则表达式
 *
 * @param input  待验证的字符串
 * @param regex  任意非空正则表达式
 * @return       true：传入的字符串满足正则规则
 */
fun isMatches(input: String?, regex: String): Boolean =
    isNotEmpty(input) && Pattern.matches(regex, input)

/**
 * 替换所有正则匹配的部分
 *
 * @param input       要替换的字符串
 * @param regex       正则表达式
 * @param replacement 代替者
 * @return 替换所有正则匹配的部分
 */
fun getReplaceAll(input: String?, regex: String, replacement: String): String = if (input == null)
    "" else Pattern.compile(regex).matcher(input).replaceAll(replacement)