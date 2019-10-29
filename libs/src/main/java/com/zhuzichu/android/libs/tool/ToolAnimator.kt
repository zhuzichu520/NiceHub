@file: JvmName("ToolAnimator")

package com.zhuzichu.android.libs.tool

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.os.Build
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator

/**
 * 默认持续时间
 */
var DEFAULT_DURATION: Long = 2000
/**
 * 默认延迟时间
 */
var DEFAULT_DELAY: Long = 0
/**
 * 默认插值器
 */
var DEFAULT_INTERPOLATOR: Interpolator = LinearInterpolator()
/**
 * 常量
 */
var ALPHA = "alpha"
var X = "x"
var Y = "y"
var TRANSLATIONX = "translationX"
var TRANSLATIONY = "translationY"
var TRANSLATIONZ = "translationZ"
var ROTATION = "rotation"
var ROTATIONX = "rotationX"
var ROTATIONY = "rotationY"
var SCALEX = "scaleX"
var SCALEY = "scaleY"

/**
 * 透明动画
 *
 * @param view 实现动画的view
 * @param f    透明度 0~1f
 */
@JvmOverloads
fun alpha(
    view: View, duration: Long = DEFAULT_DURATION, startDelay: Long = DEFAULT_DELAY,
    interpolator: Interpolator? = DEFAULT_INTERPOLATOR, vararg f: Float
) {
    val animator = ObjectAnimator.ofFloat(view, ALPHA, *f)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 定位x
 *
 * @param view 实现动画的view
 * @param x    相对屏幕的位置x
 */
@JvmOverloads
fun screenX(
    view: View, duration: Long = DEFAULT_DURATION, startDelay: Long = DEFAULT_DELAY,
    interpolator: Interpolator? = DEFAULT_INTERPOLATOR, vararg x: Float
) {
    val animator = ObjectAnimator.ofFloat(view, X, *x)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 定位y  相对于屏幕的位置
 *
 * @param view 实现动画的view
 * @param y    相对屏幕的位置y
 */
@JvmOverloads
fun screenY(
    view: View, duration: Long = DEFAULT_DURATION, startDelay: Long = DEFAULT_DELAY,
    interpolator: Interpolator? = DEFAULT_INTERPOLATOR, vararg y: Float
) {
    val animator = ObjectAnimator.ofFloat(view, Y, *y)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 平移x
 *
 * @param view 实现动画的view
 * @param x    相对之前位置坐标移动的x
 */
@JvmOverloads
fun translationX(
    view: View, duration: Long = DEFAULT_DURATION, startDelay: Long = DEFAULT_DELAY,
    interpolator: Interpolator? = DEFAULT_INTERPOLATOR, vararg x: Float
) {
    val animator = ObjectAnimator.ofFloat(view, TRANSLATIONX, *x)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 平移y
 *
 * @param view 实现动画的view
 * @param y    相对之前位置坐标移动的y
 */
@JvmOverloads
fun translationY(
    view: View, duration: Long = DEFAULT_DURATION, startDelay: Long = DEFAULT_DELAY,
    interpolator: Interpolator? = DEFAULT_INTERPOLATOR, vararg y: Float
) {
    val animator = ObjectAnimator.ofFloat(view, TRANSLATIONY, *y)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 平移z
 *
 * @param view 实现动画的view
 * @param z    相对之前位置坐标移动的z
 */
@JvmOverloads
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun translationZ(
    view: View, duration: Long = DEFAULT_DURATION, startDelay: Long = DEFAULT_DELAY,
    interpolator: Interpolator? = DEFAULT_INTERPOLATOR, vararg z: Float
) {
    val animator = ObjectAnimator.ofFloat(view, TRANSLATIONZ, *z)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 旋转
 *
 * @param view     实现动画的view
 * @param rotation 旋转的角度
 */
@JvmOverloads
fun rotation(
    view: View, duration: Long = DEFAULT_DURATION, startDelay: Long = DEFAULT_DELAY,
    interpolator: Interpolator? = DEFAULT_INTERPOLATOR, vararg rotation: Float
) {
    val animator = ObjectAnimator.ofFloat(view, ROTATION, *rotation)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 旋转x
 *
 * @param view     实现动画的view
 * @param rotation 旋转的角度
 */
@JvmOverloads
fun rotationX(
    view: View, duration: Long = DEFAULT_DURATION, startDelay: Long = DEFAULT_DELAY,
    interpolator: Interpolator? = DEFAULT_INTERPOLATOR, vararg rotation: Float
) {
    val animator = ObjectAnimator.ofFloat(view, ROTATIONX, *rotation)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 旋转y
 *
 * @param view     实现动画的view
 * @param rotation 旋转的角度
 */
@JvmOverloads
fun rotationY(
    view: View, duration: Long = DEFAULT_DURATION, startDelay: Long = DEFAULT_DELAY,
    interpolator: Interpolator? = DEFAULT_INTERPOLATOR, vararg rotation: Float
) {
    val animator = ObjectAnimator.ofFloat(view, ROTATIONY, *rotation)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 拉伸X
 *
 * @param view     实现动画的view
 * @param rotation 拉伸的倍数，可为零
 */
@JvmOverloads
fun scaleX(
    view: View, duration: Long = DEFAULT_DURATION, startDelay: Long = DEFAULT_DELAY,
    interpolator: Interpolator? = DEFAULT_INTERPOLATOR, vararg rotation: Float
) {
    val animator = ObjectAnimator.ofFloat(view, SCALEX, *rotation)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 拉伸Y
 *
 * @param view     实现动画的view
 * @param rotation 拉伸的倍数，可为零
 */
@JvmOverloads
fun scaleY(
    view: View, duration: Long = DEFAULT_DURATION, startDelay: Long = DEFAULT_DELAY,
    interpolator: Interpolator? = DEFAULT_INTERPOLATOR, vararg rotation: Float
) {
    val animator = ObjectAnimator.ofFloat(view, SCALEY, *rotation)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 动画类型外部传入
 *
 * @param view        实现动画的view
 * @param animatorStr 动画名称
 * @param rotation    动画改变的数值
 */
@JvmOverloads
fun animator(
    view: View, animatorStr: String, duration: Long = DEFAULT_DURATION,
    startDelay: Long = DEFAULT_DELAY, interpolator: Interpolator? = DEFAULT_INTERPOLATOR,
    vararg rotation: Float
) {
    val animator = ObjectAnimator.ofFloat(view, animatorStr, *rotation)
    start(animator, duration, startDelay, interpolator)
}

/**
 * 多个动画：使用起来较为麻烦，建议直接使用view.animate()的属性动画
 *
 * @param listAnimator Animator动画集合
 * @param duration     动画持续时间
 * @param startDelay   动画延迟开始时间
 * @param interpolator 插值器
 * @param playTogether 是否同时进行 true 同时执行 false 逐个执行
 */
@JvmOverloads
fun comprehensive(
    listAnimator: List<Animator>, duration: Long = DEFAULT_DURATION,
    startDelay: Long = DEFAULT_DELAY, interpolator: Interpolator? = DEFAULT_INTERPOLATOR,
    playTogether: Boolean
) {
    AnimatorSet().apply {
        if (playTogether) {
            playTogether(listAnimator)
        } else {
            playSequentially(listAnimator)
        }
        this.duration = duration
        this.startDelay = startDelay
        this.interpolator = interpolator
        start()
    }
}


/**
 * 动画开始
 *
 * @param animator     属性动画
 * @param duration     动画持续时间
 * @param startDelay   动画延迟开始时间
 * @param interpolator 插值器
 */
fun start(animator: ObjectAnimator, duration: Long, startDelay: Long, interpolator: Interpolator?) {
    var localInterpolator = interpolator
    if (localInterpolator == null) {
        localInterpolator = LinearInterpolator()
    }

    with(animator) {
        this.interpolator = localInterpolator
        this.duration = duration
        this.startDelay = startDelay
        start()
    }
}

/**
 * 快速获取透明动画
 *
 * @param view     实现动画的view
 * @param rotation 动画改变的数值
 * @return 透明动画Animator
 */
fun getAlpha(view: View, vararg rotation: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(view, ALPHA, *rotation)


/**
 * 快速获取相对屏幕x动画
 *
 * @param view     实现动画的view
 * @param rotation 动画改变的数值
 * @return 相对屏幕x动画Animator
 */
fun getX(view: View, vararg rotation: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(view, X, *rotation)

/**
 * 快速获取相对屏幕y动画
 *
 * @param view     实现动画的view
 * @param rotation 动画改变的数值
 * @return 相对屏幕y动画Animator
 */
fun getY(view: View, vararg rotation: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(view, Y, *rotation)

/**
 * 快速获取平移x动画
 *
 * @param view     实现动画的view
 * @param rotation 动画改变的数值
 * @return 平移x动画Animator
 */
fun getTranslationX(view: View, vararg rotation: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(view, TRANSLATIONX, *rotation)

/**
 * 快速获取平移y动画
 *
 * @param view     实现动画的view
 * @param rotation 动画改变的数值
 * @return 平移y动画Animator
 */
fun getTranslationY(view: View, vararg rotation: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(view, TRANSLATIONY, *rotation)

/**
 * 快速获取平移z动画
 *
 * @param view     实现动画的view
 * @param rotation 动画改变的数值
 * @return 平移z动画Animator
 */
fun getTranslationZ(view: View, vararg rotation: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(view, TRANSLATIONZ, *rotation)

/**
 * 快速获取旋转动画
 *
 * @param view     实现动画的view
 * @param rotation 动画改变的数值
 * @return 旋转动画Animator
 */
fun getRotation(view: View, vararg rotation: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(view, ROTATION, *rotation)

/**
 * 快速获取旋转x动画
 *
 * @param view     实现动画的view
 * @param rotation 动画改变的数值
 * @return 旋转x动画Animator
 */
fun getRotationX(view: View, vararg rotation: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(view, ROTATIONX, *rotation)

/**
 * 快速获取旋转y动画
 *
 * @param view     实现动画的view
 * @param rotation 动画改变的数值
 * @return 旋转y动画Animator
 */
fun getRotationY(view: View, vararg rotation: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(view, ROTATIONY, *rotation)

/**
 * 快速获取拉伸x动画
 *
 * @param view     实现动画的view
 * @param rotation 动画改变的数值
 * @return 拉伸x动画Animator
 */
fun getScaleX(view: View, vararg rotation: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(view, SCALEX, *rotation)

/**
 * 快速获取拉伸y动画
 *
 * @param view     实现动画的view
 * @param rotation 动画改变的数值
 * @return 拉伸y动画Animator
 */
fun getScaleY(view: View, vararg rotation: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(view, SCALEY, *rotation)