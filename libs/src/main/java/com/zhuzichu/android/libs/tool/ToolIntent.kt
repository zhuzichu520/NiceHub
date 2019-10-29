@file: JvmName("ToolIntent")

package com.zhuzichu.android.libs.tool

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.annotation.IntRange
import androidx.core.content.FileProvider
import java.io.File

/**
 * 启动下一个Activity
 *
 * @param context    上下文
 * @param nextActCls 下一个Activity
 */
fun <T> startActivity(context: Context, nextActCls: Class<T>) {
    startActivity(context, nextActCls, null)
}

/**
 * 启动下一个Activity
 *
 * @param context    上下文
 * @param nextActCls 下一个Activity
 * @param bundle     携带的参数Bundle,可为空
 */
fun <T> startActivity(context: Context, nextActCls: Class<T>, bundle: Bundle?) {
    val intent = Intent(context, nextActCls)
    bundle?.let { intent.putExtras(it) }

    context.startActivity(intent)
}

/**
 * 启动下一个Activity，带返回结果
 *
 * @param context     上下文 <br/>
 *                    PS：此处的参数不能传入View.getContext()，在5.0以下的手机getContext方法获取的类型为TintContextWrapper
 *                    不能转化为Activity类型，也不能传入ApplicationContext
 * @param nextActCls  下一个Activity
 * @param requestCode 请求码
 */
fun <T> startActivity4Result(context: Context, nextActCls: Class<T>, @IntRange(from = 0) requestCode: Int) {
    startActivity4Result(context, nextActCls, requestCode, null)
}

/**
 * 启动下一个Activity，带返回结果
 *
 * @param context     上下文 <br/>
 *                    PS：此处的参数不能传入View.getContext()，在5.0以下的手机getContext方法获取的类型为TintContextWrapper
 *                    不能转化为Activity类型，也不能传入ApplicationContext
 * @param nextActCls  下一个Activity
 * @param bundle      携带的参数Bundle,可为空
 * @param requestCode 请求码
 */
fun <T> startActivity4Result(context: Context, nextActCls: Class<T>, @IntRange(from = 0) requestCode: Int
                             , bundle: Bundle?) {
    val intent = Intent(context, nextActCls)
    bundle?.let { intent.putExtras(it) }

    if (context is Activity) {
        context.startActivityForResult(intent, requestCode)
    }
}

/**
 * 跳转至拨打电话面板
 *
 * @param ctx      上下文
 * @param phoneNum 电话号码
 */
fun jumpToPhonePanel(ctx: Context?, phoneNum: String?) {
    ctx?.let {
        val telNum = phoneNum ?: ""
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telNum"))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        it.startActivity(intent)
    }
}

/**
 * 跳转至系统浏览器，如果存在多个浏览器，会先弹出浏览器选择页面
 *
 * @param ctx 上下文
 * @param url 跳转地址，必须以http://或者https://开头
 */
fun jumpToSystemBrowser(ctx: Context?, url: String?) {
    if (ctx == null || url == null) {
        return
    }

    //url地址如果未以http或者https开头，程序会崩溃，因此捕捉异常
    try {
        ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * 跳转至系统相册，同时带结果返回
 *
 * @param ctx         上下文 <br/>
 *                    PS：此处的参数不能传入View.getContext()，在5.0以下的手机getContext方法获取的类型为TintContextWrapper
 *                    不能转化为Activity类型，也不能传入ApplicationContext
 * @param requestCode 请求码
 */
fun jumpToPhotoAlbum(ctx: Context?, @IntRange(from = 0) requestCode: Int) {
    ctx?.let { context ->
        val intent = Intent(Intent.ACTION_PICK, null).apply {
            setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        }

        if (context is Activity && hasActivityByIntent(context, intent)) {
            context.startActivityForResult(intent, requestCode)
        }
    }
}

/**
 * 打开系统拍照，同时带结果返回 <br/>
 * PS:没有做权限判断 <br/>
 * 另外需要在manifest.xml中'provider'标签，主要用于targetSdkVersion 24以上的兼容处理
 *
 * @param ctx         上下文 <br/>
 *                    PS：此处的参数不能传入View.getContext()，在5.0以下的手机getContext方法获取的类型为TintContextWrapper
 *                    不能转化为Activity类型，也不能传入ApplicationContext
 * @param file        拍照之后存储的图片文件 <br/>
 *                    PS:如果指定了存储文件，那么在onActivityResult方法中返回Intent中data为null <br/>
 *                    如果未指定（即传入null），那么系统会存储到默认的存储路径中，拍摄的照片将返回一个缩略图，可以通过data.getParcelableExtra("data")<br/>
 *                    值得注意的是：有些机型即使不指定图片的存储路径，也会返回null
 * @param requestCode 请求码
 */
fun jumpToSystemCamera(ctx: Context?, file: File?, @IntRange(from = 0) requestCode: Int) {
    ctx?.let { context ->
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            .apply { addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION) }

        if (file != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(context, file))
        }

        if (context is Activity && hasActivityByIntent(context, intent)) {
            context.startActivityForResult(intent, requestCode)
        }
    }
}

/**
 * 获取文件类型的uri
 *
 * @param ctx  上下文
 * @param file 源文件
 * @return 文件类型的uri
 */
fun getUriForFile(ctx: Context, file: File): Uri {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) FileProvider.getUriForFile(ctx
        , "${ctx.packageManager}.fileprovider", file) else Uri.fromFile(file)
}

/**
 * 跳转到应用详细信息界面
 *
 * @param ctx 上下文
 */
fun jumpToAppDetail(ctx: Context?) {
    ctx?.let { context ->
        var intent = context.packageManager.getLaunchIntentForPackage("com.iqoo.secure")

        if (intent == null) {
            intent = context.packageManager.getLaunchIntentForPackage("com.oppo.safe")
        }

        if (intent == null) {
            intent = Intent().apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                data = Uri.fromParts("package", context.packageName, null)
            }
        }

        if (hasActivityByIntent(context, intent)) {
            context.startActivity(intent)
        }
    }
}

/**
 * 跳转至系统设置界面
 *
 * @param ctx 上下文
 */
fun jumpToSetting(ctx: Context?) {
    ctx?.let {
        val intent = Intent(Settings.ACTION_SETTINGS)
        if (hasActivityByIntent(it, intent)) it.startActivity(intent)
    }
}

/**
 * 跳转至推送设置页面 <br/>
 * PS:当系统版本是8.0或者8.0以上时，会直接跳转至通知设置页面 <br/>
 * 反之则跳转至应用详细信息界面
 *
 * @param ctx 上下文
 */
fun jumpToNotificationSetting(ctx: Context?) {
    ctx?.let {
        /*8.0以上跳转至推送设置页面*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intent = Intent().apply {
                action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                putExtra(Settings.EXTRA_APP_PACKAGE, it.packageName)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            if (hasActivityByIntent(it, intent)) it.startActivity(intent)

        } else jumpToAppDetail(it)
    }
}

/**
 * 跳转至谷歌商店
 *
 * @param ctx         上下文
 * @param packageName 应用包名
 * @return true:跳转成功， 反之跳转失败
 */
fun jumpToGooglePlay(ctx: Context?, packageName: String?): Boolean {
    if (ctx == null || packageName.isNullOrEmpty()) {
        return false
    }

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
        .apply { setPackage("com.android.vending") }

    return if (hasActivityByIntent(ctx, intent)) {
        ctx.startActivity(intent)
        true
    } else {
        false
    }
}

/**
 * 根据Intent判断，是否存在该Activity<br/>
 * 为了跳转之前做判断，防止崩溃
 *
 * @param ctx     上下文
 * @param intent  Intent
 * @return true:存在，可以跳转，反之则不存在
 */
fun hasActivityByIntent(ctx: Context, intent: Intent): Boolean {
    return intent.resolveActivity(ctx.packageManager) != null
}