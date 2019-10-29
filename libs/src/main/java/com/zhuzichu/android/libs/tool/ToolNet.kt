@file: JvmName("ToolNet")

package com.zhuzichu.android.libs.tool

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi

/**
 * 未知
 */
private const val UNKNOWN = "unknown"
/**
 *
 */
private const val WIFI = "wifi"
/**
 * 2G网络类型
 */
private const val NETWORK_2G = "2G"
/**
 * 3G网络类型
 */
private const val NETWORK_3G = "3G"
/**
 * 4G网络类型
 */
private const val NETWORK_4G = "4G"
/**
 * Current network is LTE_CA
 */
private const val NETWORK_TYPE_LTE_CA = 19

/**
 * 网络是否连接
 *
 * @param ctx 上下文
 * @return true 已连接
 */
fun isConnected(ctx: Context) = getActiveNetWorkInfo(ctx)?.isConnected ?: false

/**
 * Wifi是否连接
 *
 * @param ctx 上下文
 * @return true 已连接
 */
fun isWifiConnected(ctx: Context): Boolean {
    val networkInfo = getActiveNetWorkInfo(ctx) ?: return false
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return networkInfo.isConnected &&
                getNetworkCapabilities(ctx)?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false
    }
    return networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI
}

/**
 * 移动网络是否连接
 *
 * @param ctx 上下文
 * @return true 已连接
 */
fun isMobileConnected(ctx: Context): Boolean {
    val networkInfo = getActiveNetWorkInfo(ctx) ?: return false
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return networkInfo.isConnected &&
                getNetworkCapabilities(ctx)?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false
    }
    return networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_MOBILE
}

/**
 * 获取移动网路运营商名称
 *
 * @param ctx 上下文
 * @return 运营商名称
 */
fun getNetWorkOperatorName(ctx: Context): String {
    val telephonyManager: TelephonyManager? = ctx
        .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return telephonyManager?.networkOperatorName ?: ""
}

/**
 * 打开网络设置界面
 * 打开设置界面
 *
 * @param ctx 上下文
 */
fun openWirelessSettings(ctx: Context) {
    runCatching {
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ctx.startActivity(intent)
    }
}

/**
 * 获取连接的网络类型
 * wifi  2G 3G 4G  未知等
 *
 * @param ctx 上下文
 * @return 网络类型
 */
fun getNetWorkState(ctx: Context): String = when {
    isWifiConnected(ctx) -> WIFI
    isMobileConnected(ctx) -> getMobileNetWorkType(ctx)
    else -> UNKNOWN
}


/**
 * 获取手机网络类型
 *
 * @param ctx 上下文
 * @return 3g 4g 等
 */
fun getMobileNetWorkType(ctx: Context): String {
    val telephonyManager: TelephonyManager? =
        ctx.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return getNetworkString(telephonyManager?.networkType ?: TelephonyManager.NETWORK_TYPE_UNKNOWN)
}

/**
 * 获取根据netWorkType获取几G网络类型
 * 模仿TelephonyManager中@hide方法getNetWorkClass
 *
 * @param networkType 网络类型
 * @return 2G|3G|4G|UNKNOW
 */
fun getNetworkString(networkType: Int): String {
    when (networkType) {
        TelephonyManager.NETWORK_TYPE_GPRS,
        TelephonyManager.NETWORK_TYPE_GSM,
        TelephonyManager.NETWORK_TYPE_EDGE,
        TelephonyManager.NETWORK_TYPE_CDMA,
        TelephonyManager.NETWORK_TYPE_1xRTT,
        TelephonyManager.NETWORK_TYPE_IDEN -> return NETWORK_2G

        TelephonyManager.NETWORK_TYPE_UMTS,
        TelephonyManager.NETWORK_TYPE_EVDO_0,
        TelephonyManager.NETWORK_TYPE_EVDO_A,
        TelephonyManager.NETWORK_TYPE_HSDPA,
        TelephonyManager.NETWORK_TYPE_HSUPA,
        TelephonyManager.NETWORK_TYPE_HSPA,
        TelephonyManager.NETWORK_TYPE_EVDO_B,
        TelephonyManager.NETWORK_TYPE_EHRPD,
        TelephonyManager.NETWORK_TYPE_HSPAP,
        TelephonyManager.NETWORK_TYPE_TD_SCDMA -> return NETWORK_3G

        TelephonyManager.NETWORK_TYPE_LTE,
        TelephonyManager.NETWORK_TYPE_IWLAN,
        NETWORK_TYPE_LTE_CA -> return NETWORK_4G

        else -> return UNKNOWN
    }
}

/**
 * 获取网络信息
 * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
 *
 * @param ctx 上下文
 * @return 网络信息类
 */
@SuppressLint("MissingPermission")
private fun getActiveNetWorkInfo(ctx: Context): NetworkInfo? =
    getConnectivityManager(ctx)?.activeNetworkInfo

/**
 * 获取当前网络链接的NetworkCapabilities
 */
@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("MissingPermission")
private fun getNetworkCapabilities(ctx: Context): NetworkCapabilities? {
    val connectivityManager = getConnectivityManager(ctx);
    return connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
}

/**
 * 获取网络链接manager `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
 */
@SuppressLint("MissingPermission")
private fun getConnectivityManager(ctx: Context): ConnectivityManager? =
    ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager