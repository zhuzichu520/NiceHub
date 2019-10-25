package com.zhuzichu.android.shared.extension

import android.annotation.SuppressLint
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

inline fun <reified T> itemBindingOf(variableId: Int, @LayoutRes layoutRes: Int): ItemBinding<T> =
    ItemBinding.of(variableId, layoutRes)

inline fun <reified T> itemBindingOf(
    noinline onItemBind: (
        @ParameterName("itemBinding") ItemBinding<in T>,
        @ParameterName("position") Int,
        @ParameterName("item") T
    ) -> Unit
): ItemBinding<T> = ItemBinding.of(onItemBind)

inline fun <reified T> OnItemBind<T>.toItemBinding(): ItemBinding<T> =
    ItemBinding.of(this)

inline fun <reified T> OnItemBindClass<in T>.map(variableId: Int, @LayoutRes layoutRes: Int) {
    map(T::class.java, variableId, layoutRes)
}

inline fun <reified T> OnItemBindClass<in T>.map(
    noinline onItemBind: (
        @ParameterName("itemBinding") ItemBinding<in T>,
        @ParameterName("position") Int,
        @ParameterName("item") T
    ) -> Unit
) {
    map(T::class.java) { itemBinding, position, item ->
        onItemBind(
            itemBinding as ItemBinding<in T>,
            position,
            item
        )
    }
}

inline fun <reified T> itemDiffOf(crossinline funcation: (oldItem: T, newItem: T) -> Boolean): DiffUtil.ItemCallback<Any> {
    return object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return if (oldItem is T && newItem is T) {
                funcation(oldItem, newItem)
            } else oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
    }
}
