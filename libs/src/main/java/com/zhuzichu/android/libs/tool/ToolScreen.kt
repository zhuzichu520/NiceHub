@file: JvmName("ToolScreen")

package com.zhuzichu.android.libs.tool

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.view.Surface
import android.view.View
import android.view.WindowManager
import android.widget.ImageView

/**
 * 获取屏幕密度
 * @param context context
 * @return 屏幕宽度像素值
 */
fun getScreenDensity(context: Context): Float {
    return context.resources.displayMetrics.density
}

/**
 * 获取屏幕宽度像素值
 *
 * @param context context
 * @return 屏幕宽度像素值
 */
fun getScreenWidth(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

/**
 * 获取屏幕高度像素值
 * 在有些机型上受menu bar的影响, 获取到的高度小于真实高度
 * 需要获取屏幕真实高度时请使用 [.getRealScreenSize]
 *
 * @param context context
 * @return 屏幕高度像素值
 */
fun getScreenHeight(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}

/**
 * 获取屏幕真实宽高(适用于SDK>17)
 * 包含status bar和menu bar
 *
 * @param context context
 * @return 获取屏幕真实宽高 int[0]-宽 int[1]-高
 */
fun getRealScreenSize(context: Context): IntArray {
    val size = IntArray(2)
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE)

    when (windowManager) {
        is WindowManager -> {
            val display = windowManager.defaultDisplay
            with(Point()) {
                display.getRealSize(this)
                size[0] = this.x
                size[1] = this.y
            }
        }
        else -> return size
    }
    return size
}

/**
 * 获取屏幕旋转角度
 *
 * @param context context
 * @return 屏幕旋转角度，值为竖直{@link Surface#ROTATION_0}, 逆时针旋转90度{@link Surface#ROTATION_90}
 * , 倒立{@link Surface#ROTATION_180}, 顺时针旋转90度{@link Surface#ROTATION_270}之一
 */
fun getScreenRotation(context: Context): Int {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE)
    return when (windowManager) {
        is WindowManager -> windowManager.defaultDisplay.rotation
        else -> Surface.ROTATION_0
    }
}

/**
 * 从一个view创建Bitmap
 * 绘制之前要清掉View的焦点，因为焦点可能会改变一个View的UI状态。
 *
 * @param view 需要转换成Bitmap的view
 * @return 根据view生成的bitmap, 可能为空
 */
fun createBitmapFromView(view: View): Bitmap {
    if (view is ImageView) {
        val drawable = view.drawable

        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
    }

    view.clearFocus()
    val bitmap: Bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

    Canvas().apply {
        setBitmap(bitmap)
        save()
        drawColor(Color.WHITE)
        view.draw(this)
        restore()
        setBitmap(null)
    }
    return bitmap
}

/**
 * 进入或退出全屏
 *
 * @param activity activity
 */
fun toggleFullScreen(activity: Activity) {
    val params = activity.window.attributes
    val window = activity.window

    if (params.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    } else {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}

/**
 * 设置activity为横屏
 *
 * @param activity activity
 */
fun setLandScape(activity: Activity) {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}

/**
 * 设置activity为竖屏
 *
 * @param activity activity
 */
fun setPortrait(activity: Activity) {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}