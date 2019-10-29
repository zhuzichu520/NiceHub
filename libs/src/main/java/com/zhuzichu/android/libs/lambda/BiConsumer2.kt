package com.zhuzichu.android.libs.lambda

interface BiConsumer2<T, U> {
    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     */
    fun accept(t: T, u: U)
}
