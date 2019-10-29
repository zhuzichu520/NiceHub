package com.zhuzichu.android.widget.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.FloatRange
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import kotlin.math.roundToInt

class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val borderWidthSmall: Int
    private val borderWidthLarge: Int
    private val outerPaint: Paint
    private val whitePaint: Paint
    private val innerPaint: Paint
    private var selected: Boolean = false

    init {
        val r = this.resources
        this.borderWidthSmall = TypedValue.applyDimension(1, 3.0f, r.displayMetrics).toInt()
        this.borderWidthLarge = TypedValue.applyDimension(1, 5.0f, r.displayMetrics).toInt()
        this.whitePaint = Paint()
        this.whitePaint.isAntiAlias = true
        this.whitePaint.color = -1
        this.innerPaint = Paint()
        this.innerPaint.isAntiAlias = true
        this.outerPaint = Paint()
        this.outerPaint.isAntiAlias = true
        this.update(-12303292)
        this.setWillNotDraw(false)
    }


    @ColorInt
    private fun translucentColor(color: Int): Int {
        val alpha = (Color.alpha(color).toFloat() * 0.7f).roundToInt()
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    @ColorInt
    fun shiftColor(@ColorInt color: Int, @FloatRange(from = 0.0, to = 2.0) by: Float): Int {
        return if (by == 1.0f) {
            color
        } else {
            val hsv = FloatArray(3)
            Color.colorToHSV(color, hsv)
            hsv[2] *= by
            Color.HSVToColor(hsv)
        }
    }


    @ColorInt
    fun shiftColorDown(@ColorInt color: Int): Int {
        return shiftColor(color, 0.9f)
    }

    @ColorInt
    fun shiftColorUp(@ColorInt color: Int): Int {
        return shiftColor(color, 1.1f)
    }

    private fun update(@ColorInt color: Int) {
        this.innerPaint.color = color
        this.outerPaint.color = shiftColorDown(color)
    }

    override fun setBackgroundColor(@ColorInt color: Int) {
        this.update(color)
        this.requestLayout()
        this.invalidate()
    }

    override fun setBackgroundResource(@ColorRes color: Int) {
        this.setBackgroundColor(ContextCompat.getColor(this.context, color))
    }


    override fun setBackground(background: Drawable) {
        throw IllegalStateException("Cannot use setBackground() on CircleView.")
    }


    override fun setBackgroundDrawable(background: Drawable) {
        throw IllegalStateException("Cannot use setBackgroundDrawable() on CircleView.")
    }


    override fun setActivated(activated: Boolean) {
        throw IllegalStateException("Cannot use setActivated() on CircleView.")
    }

    override fun setSelected(selected: Boolean) {
        this.selected = selected
        this.requestLayout()
        this.invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
        this.setMeasuredDimension(this.measuredWidth, this.measuredWidth)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val outerRadius = this.measuredWidth / 2
        if (this.selected) {
            val whiteRadius = outerRadius - this.borderWidthLarge
            val innerRadius = whiteRadius - this.borderWidthSmall
            canvas.drawCircle(
                (this.measuredWidth / 2).toFloat(),
                (this.measuredHeight / 2).toFloat(),
                outerRadius.toFloat(),
                this.outerPaint
            )
            canvas.drawCircle(
                (this.measuredWidth / 2).toFloat(),
                (this.measuredHeight / 2).toFloat(),
                whiteRadius.toFloat(),
                this.whitePaint
            )
            canvas.drawCircle(
                (this.measuredWidth / 2).toFloat(),
                (this.measuredHeight / 2).toFloat(),
                innerRadius.toFloat(),
                this.innerPaint
            )
        } else {
            canvas.drawCircle(
                (this.measuredWidth / 2).toFloat(),
                (this.measuredHeight / 2).toFloat(),
                outerRadius.toFloat(),
                this.innerPaint
            )
        }

    }

    fun createSelector(color: Int): Drawable {
        val darkerCircle = ShapeDrawable(OvalShape())
        darkerCircle.paint.color = translucentColor(shiftColorUp(color))
        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(intArrayOf(16842919), darkerCircle)
        return stateListDrawable
    }

    fun showHint(color: Int) {
        val screenPos = IntArray(2)
        val displayFrame = Rect()
        this.getLocationOnScreen(screenPos)
        this.getWindowVisibleDisplayFrame(displayFrame)
        val context = this.context
        val width = this.width
        val height = this.height
        val midy = screenPos[1] + height / 2
        var referenceX = screenPos[0] + width / 2
        if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_LTR) {
            val screenWidth = context.resources.displayMetrics.widthPixels
            referenceX = screenWidth - referenceX
        }

        val cheatSheet =
            Toast.makeText(context, String.format("#%06X", 16777215 and color), Toast.LENGTH_SHORT)
        if (midy < displayFrame.height()) {
            cheatSheet.setGravity(8388661, referenceX, screenPos[1] + height - displayFrame.top)
        } else {
            cheatSheet.setGravity(81, 0, height)
        }

        cheatSheet.show()
    }
}