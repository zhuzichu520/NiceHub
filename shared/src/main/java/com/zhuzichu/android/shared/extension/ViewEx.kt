package com.zhuzichu.android.shared.extension

import androidx.core.view.forEachIndexed
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

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