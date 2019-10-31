package com.zhuzichu.android.shared.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import androidx.core.view.forEachIndexed
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

fun BottomNavigationView.setupWithViewPager(viewPager: ViewPager) {
    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            this@setupWithViewPager.menu.getItem(position).isChecked = true
        }
    })
    this.setOnNavigationItemSelectedListener {
        this@setupWithViewPager.menu.forEachIndexed { index, item ->
            if (it.itemId == item.itemId) {
                viewPager.setCurrentItem(index, false)
            }
        }
        true
    }
}

@Suppress("DEPRECATION")
@SuppressLint("ObsoleteSdkInt")
fun Context.localeContextWrapper(newLocale: Locale): ContextWrapper {
    var context: Context = this
    val config = this.resources.configuration

    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
            Locale.setDefault(newLocale)
            config.setLocale(newLocale)
            val localeList = LocaleList(newLocale)
            LocaleList.setDefault(localeList)
            config.setLocales(localeList)
            context = this.createConfigurationContext(config)
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 -> {
            config.setLocale(newLocale)
            context = this.createConfigurationContext(config)
        }
        else -> {
            config.locale = newLocale
            this.resources.updateConfiguration(config, this.resources.displayMetrics)
        }
    }
    return ContextWrapper(context)
}