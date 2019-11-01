package com.zhuzichu.android.shared.base

import android.content.Context
import android.content.res.Configuration
import com.umeng.analytics.MobclickAgent
import com.zhuzichu.android.mvvm.base.BaseActivity
import com.zhuzichu.android.shared.extension.localeContextWrapper
import com.zhuzichu.android.shared.storage.GlobalStorage
import java.util.*

abstract class ActivityAnalyticsBase : BaseActivity() {

    private var globalStorage: GlobalStorage? = GlobalStorage()

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            super.attachBaseContext(
                it.localeContextWrapper(
                    Locale(
                        globalStorage?.locale ?: Locale.getDefault().country
                    )
                )
            )
        }
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        //主题和语言切换产生冲突 具体详见
        // https://stackoverflow.com/questions/55265834/change-locale-not-work-after-migrate-to-androidx
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        globalStorage = null
    }
}