package com.zhuzichu.android.widget.toolbar

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.TextViewCompat
import com.zhuzichu.android.widget.R

class NiceToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val root: View? = LayoutInflater.from(context)
        .inflate(R.layout.layout_toolbar, this, true)
    private val titleView: TextView = findViewById(R.id.title)
    private val navigationTextView: TextView = findViewById(R.id.navigation_text)
    private val navigationIconView: ImageView = findViewById(R.id.navigation_icon)

    var titleText: String? = null
        set(value) {
            value?.let {
                field = value
                titleView.text = it
            }
        }

    var titleTextAppearance: Int? = null
        set(value) {
            value?.let {
                field = value
                TextViewCompat.setTextAppearance(titleView, it)
            }
        }

    var navigationText: String? = null
        set(value) {
            value?.let {
                field = value
                navigationTextView.text = it
            }
        }

    private var navigationDrawable: Drawable? = null
        set(value) {
            value?.let {
                field = value
                navigationIconView.setImageDrawable(it)
            }
        }

    var navigationIcon: Int? = null
        set(value) {
            value?.let {
                field = value
                navigationDrawable = AppCompatResources.getDrawable(context, it)
            }
        }

    var onOnClickNavigationListener: OnClickListener? = null
        set(value) {
            value?.let {
                field = value
                root?.findViewById<View>(R.id.navigation_layout)?.setOnClickListener(it)
            }
        }

    init {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.NiceToolbar, 0, 0)
        titleText = arr.getString(R.styleable.NiceToolbar_toolbarTitle)
        navigationText = arr.getString(R.styleable.NiceToolbar_toolbarNavigationText)
        navigationDrawable = arr.getDrawable(R.styleable.NiceToolbar_toolbarNavigationIcon)
        titleTextAppearance = arr.getResourceId(
            R.styleable.NiceToolbar_toolbarTitleTextAppearance,
            R.attr.titleTextAppearance
        )
        arr.recycle()
    }
}