@file: JvmName("ToolView")

package com.zhuzichu.android.libs.tool

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.annotation.*
import androidx.core.content.ContextCompat

@IntDef(View.VISIBLE, View.GONE, View.INVISIBLE)
@Retention(AnnotationRetention.SOURCE)
annotation class ViewVisibility

/**
 * 设置控件Click监听事件
 *
 * @param onClickListener 监听实例
 * @param views           视图集合
 */
fun setOnClickListener(onClickListener: View.OnClickListener?, vararg views: View?) {
    if (views.isNotEmpty() && onClickListener != null) {
        for (view in views) {
            view?.setOnClickListener(onClickListener)
        }
    }
}

/**
 * 显示View视图
 *
 * @param views 视图集合
 */
fun showView(vararg views: View?) = setVisibility(View.VISIBLE, *views)

/**
 * 以GONE方式，隐藏View视图
 *
 * @param views 视图集合
 */
fun hideView(vararg views: View?) = setVisibility(View.GONE, *views)

/**
 * 设置View显示/隐藏
 *
 * @param visibility 值见:[View.VISIBLE]、[View.GONE]、[View.INVISIBLE]
 * @param views      视图集合
 */
fun setVisibility(@ViewVisibility visibility: Int, vararg views: View?) {
    if (views.isNotEmpty()) {
        views.forEach {
            it?.visibility = visibility
        }
    }
}

/**
 * 给TextView 设置
 *
 * @param tv         TextView
 * @param resIdOrTxt 值
 */
fun setText(tv: TextView?, resIdOrTxt: Any?) {
    when (resIdOrTxt) {
        is String -> tv?.text = resIdOrTxt
        is CharSequence -> tv?.text = resIdOrTxt
        is Int -> tv?.setText(resIdOrTxt)
    }
}

/**
 * 设置多个文本颜色
 *
 * @param ctx       上下文
 * @param colorRes  色值资源，例如R.color.xx
 * @param textViews TextView集合
 */
fun setTextColor(ctx: Context?, @ColorRes colorRes: Int, vararg textViews: TextView?) {
    if (ctx == null || textViews.isEmpty()) {
        return
    }

    for (tv in textViews) {
        tv?.setTextColor(ContextCompat.getColor(ctx, colorRes))
    }
}

/**
 * 设置多个文本颜色
 *
 * @param colorString 以'#'开头的色值字符串，例如：#RRGGBB，#AARRGGBB
 * @param textViews   TextView集合
 */
fun setTextColor(@Size(min = 7, max = 9) colorString: String?, vararg textViews: TextView?) {
    if (isEmptyOrNull(colorString) || textViews.isEmpty()) {
        return
    }

    // 色值字符串检测
    if (colorString!![0] != '#' || (colorString.length != 7 && colorString.length != 9)) return

    textViews.forEach { it?.setTextColor(Color.parseColor(colorString)) }
}

/**
 * 设置View的Tag
 *
 * @param view 视图
 * @param tag  Tag值
 */
fun setTag(view: View?, tag: Any?) {
    tag?.let { view?.tag = it }
}

/**
 * 以指定的key，设置View的Tag
 *
 * @param view   视图
 * @param tagKey Tag的Key值，注意要传入Ids资源文件中定义的id值，例如：R.id.x
 * @param tag    Tag值
 */
fun setTag(view: View?, @IdRes tagKey: Int, tag: Any?) {
    if (tagKey.ushr(24) >= 2) {
        tag?.let { view?.setTag(tagKey, it) }
    }
}

/**
 * 设置视图的背景图片
 *
 * @param view  视图
 * @param resId 背景图片资源，例如R.drawable.xx
 */
fun setBackgroundResource(view: View?, @DrawableRes resId: Int) = view?.setBackgroundResource(resId)

/**
 * 设置视图的背景色
 *
 * @param view     视图
 * @param colorRes 背景色值，例如R.color.xx
 */
fun setBackgroundColor(view: View?, @ColorRes colorRes: Int) =
    view?.setBackgroundColor(ContextCompat.getColor(view.context, colorRes))