package com.zhuzichu.android.shared.extension

import android.app.Activity
import androidx.fragment.app.Fragment
import com.zhuzichu.android.libs.tool.toCast
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <U, T> Activity.bindExtra(key: String) = BindLoader<U, T>(key)

fun <U, T> Fragment.bindArgument(key: String) = BindLoader<U, T>(key)

private class IntentDelegate<in U, out T>(private val key: String) : ReadOnlyProperty<U, T?> {
    override fun getValue(thisRef: U, property: KProperty<*>): T? {
        return when (thisRef) {
            is Fragment -> thisRef.arguments?.get(key)?.toCast()
            else -> (thisRef as Activity).intent?.extras?.get(key)?.toCast()
        }
    }

}

class BindLoader<in U, out T>(private val key: String) {
    operator fun provideDelegate(thisRef: U, prop: KProperty<*>): ReadOnlyProperty<U, T?> {
        return IntentDelegate(key)
    }
}