package com.zhuzichu.android.widget.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class PaginationRecyclerView(context: Context, attrs: AttributeSet?) :
    RecyclerView(context, attrs) {
    private var scrollListener: EndlessRecyclerViewScrollListener? = null
    private var onScrollBottomListener: OnScrollBottomListener? = null

    override fun setLayoutManager(layout: LayoutManager?) {
        super.setLayoutManager(layout)
        if (scrollListener == null && layout != null) {
            scrollListener = object : EndlessRecyclerViewScrollListener(layout) {
                override fun onScrollBottom(view: RecyclerView?, totalItemsCount: Int) {
                    onScrollBottomListener?.onScrollBottom()
                }
            }
        }
        scrollListener?.let { addOnScrollListener(it) }
    }

    fun setOnPageChangeListener(onScrollBottomListener: OnScrollBottomListener) {
        this.onScrollBottomListener = onScrollBottomListener
    }
}