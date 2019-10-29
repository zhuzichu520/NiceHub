@file:JvmName("ToolBitmap")

package com.zhuzichu.android.libs.tool

import android.graphics.*
import android.media.ExifInterface
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 * desc: 图片工具类 <br/>
 * PS:此工具类需要两次测试，若验证方法是否能正常运行，请运行单元测试内容'ToolBitmapTest'<br/>
 * 若验证图片展示是否正常，请运行App项目中'TestToolBitmapActivity'<br/>
 * time: 2019/6/5 上午10:47 <br/>
 * author: 匡衡 <br/>
 * since V 1.3.0.6
 */

/**
 * 清除回收图片
 *
 * @param bitmaps 图片集合
 */
fun recycleBitmaps(vararg bitmaps: Bitmap?) {
    bitmaps.forEach { bitmap -> if (bitmap != null && !bitmap.isRecycled) bitmap.recycle() }
}

/**
 * 根据角度，旋转图片 <br/>
 *
 * @param degrees 旋转角度
 * @param bitmap  原始图片
 * @return 旋转后的图片。如果传入的原图为空，则返回空
 */
fun rotatingBitmap(@FloatRange(from = 0.0, to = 360.0) degrees: Float, bitmap: Bitmap?): Bitmap? {
    if (bitmap == null) {
        return null
    }

    if (degrees <= 0f) {
        return bitmap
    }

    val matrix = Matrix()
    matrix.postRotate(degrees)

    return try {
        Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
        return bitmap
    }

}

/**
 * 读取图片属性：旋转的角度
 *
 * @param filePath 图片绝对路径
 * @return degree旋转的角度, 默认返回'0'
 */
fun readPictureDegree(filePath: String?): Int {
    if (filePath.isNullOrEmpty()) {
        return 0
    }

    return try {
        when (ExifInterface(filePath).getAttributeInt(ExifInterface.TAG_ORIENTATION
            , ExifInterface.ORIENTATION_NORMAL)) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }

    } catch (e: IOException) {
        e.printStackTrace()
        0
    }
}

/**
 * @param bitmap Bitmap
 * @return 获取bitmap的config, 为空则返回ARGB.8888
 */
fun getBitmapConfig(bitmap: Bitmap?): Bitmap.Config {
    return bitmap?.config ?: Bitmap.Config.ARGB_8888
}

/**
 * 图片灰处理 <br/>
 * 以原始图片为模板重新绘制图片，绘制中加入灰色蒙板<br/>
 *
 * @param originalBitmap 原始图片
 * @param greyBitmap     被绘制上灰色的图片
 * @return 灰处理后的图片
 */
fun changBitmapToGrey(originalBitmap: Bitmap?, greyBitmap: Bitmap?): Bitmap? {
    originalBitmap ?: return null

    //创建承载灰色蒙板的图片实例
    val changeGreyBitmap = greyBitmap
        ?: Bitmap.createBitmap(originalBitmap.width, originalBitmap.height, getBitmapConfig(originalBitmap))
    val canvas = Canvas(changeGreyBitmap)
    val paint = Paint()
    //创建颜色变换矩阵
    val colorMatrix = ColorMatrix()
    //设置灰度影响范围
    colorMatrix.setSaturation(0f)
    //设置画笔的颜色过滤矩阵
    paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
    //使用处理后的画笔绘制图像
    canvas.drawBitmap(originalBitmap, 0f, 0f, paint)

    return changeGreyBitmap
}

/**
 * 根据Config 获取压缩后的图片 <br/>
 * PS:同时回收原始图片，即recycler掉
 *
 * @param bitmap  原图
 * @param config  {@link Bitmap.Config}
 * @param quality 压缩值，范围在0-100数值
 * @return 压缩后的图片，图片格式为JPEG
 */
fun getCompressBitmapByConfigAndQuality(bitmap: Bitmap?, config: Bitmap.Config, @IntRange(from = 0, to = 100) quality: Int)
        : Bitmap? {
    if (bitmap == null) {
        return null
    }

    val bos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos)

    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = false
        inSampleSize = 1
        inPreferredConfig = config
    }

    val outBm = BitmapFactory.decodeByteArray(bos.toByteArray(), 0, bos.toByteArray().size, options)
    recycleBitmaps(bitmap)

    return outBm
}

/**
 * 获取压缩后的图片 <br/>
 * 由于创建图片时Options的inSampleSize参数为int类型，压缩后无法真正达到目标所希望的图片大小，只能是接近目标宽高值
 *
 * @param filePath 图片文件地址
 * @param config   {@link Bitmap.Config}
 * @param destW    压缩后的目标宽度
 * @param destH    压缩后的目标高度
 * @param quality  压缩值，范围在0-100数值
 * @return 压缩后的图片
 */
fun getCompressBitmap(filePath: String?, config: Bitmap.Config, destW: Int, destH: Int
                      , @IntRange(from = 0, to = 100) quality: Int): Bitmap? {
    if (filePath.isNullOrEmpty() || destH <= 0 || destW <= 0) {
        return null
    }

    val options = BitmapFactory.Options().apply {
        inPreferredConfig = config
        inJustDecodeBounds = true
    }

    // 获取这个图片的宽和高
    BitmapFactory.decodeFile(filePath, options)
    options.inJustDecodeBounds = false
    // 压缩宽高比例
    val blW = (options.outWidth / destW).toFloat()
    val blH = (options.outHeight / destH).toFloat()
    options.inSampleSize = 1

    if (blH > 1 || blW > 1) {
        val bl = if (blW > blH) blW else blH
        options.inSampleSize = (bl + 0.9f).toInt() // 尽量不失真
    }

    var bitmap = BitmapFactory.decodeFile(filePath, options)
    if (quality != 100) {
        bitmap = getCompressBitmapByConfigAndQuality(bitmap, config, quality)
    }

    return when (val degree = readPictureDegree(filePath)) {
        0 -> bitmap
        else -> rotatingBitmap(degree.toFloat(), bitmap)
    }
}