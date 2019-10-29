package com.zhuzichu.android.mvvm.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.navigation.AnimBuilder
import com.zhuzichu.android.mvvm.event.SingleLiveEvent

open class BaseViewModel : ViewModel(), LifecycleViewModel, IBaseCommon {

    val uc by lazy { UIChangeLiveData() }

    lateinit var lifecycleOwner: LifecycleOwner

    fun injectLifecycleOwner(viewLifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = viewLifecycleOwner
    }

    override fun startActivity(
        clz: Class<*>,
        argument: BaseArgument,
        isPop: Boolean,
        options: Bundle,
        requestCode: Int
    ) {
        val playload = Payload.Activity(clz)
        playload.argument = argument
        playload.isPop = isPop
        playload.options = options
        playload.requestCode = requestCode
        uc.startActivityEvent.value = playload
    }

    override fun startFragment(
        actionId: Int,
        argument: BaseArgument,
        animBuilder: AnimBuilder.() -> Unit
    ) {
        val playload = Payload.Fragment(actionId)
        playload.argument = argument
        playload.animBuilder = animBuilder
        uc.startFragmentEvent.value = playload
    }

    override fun back() {
        uc.onBackPressedEvent.call()
    }

    override fun showLoading() {
        uc.showLoadingEvent.call()
    }

    override fun hideLoading() {
        uc.hideLoadingEvent.call()
    }

    override fun toast(text: String?) {
        uc.toastStringEvent.value = text
    }

    override fun toast(@StringRes id: Int) {
        uc.toastStringResEvent.value = id
    }

    inner class UIChangeLiveData {
        internal val startActivityEvent: SingleLiveEvent<Payload.Activity> = SingleLiveEvent()
        internal val startFragmentEvent: SingleLiveEvent<Payload.Fragment> = SingleLiveEvent()
        internal val onBackPressedEvent: SingleLiveEvent<Any> = SingleLiveEvent()
        internal val showLoadingEvent: SingleLiveEvent<Any> = SingleLiveEvent()
        internal val hideLoadingEvent: SingleLiveEvent<Any> = SingleLiveEvent()
        internal val toastStringEvent: SingleLiveEvent<String?> = SingleLiveEvent()
        internal val toastStringResEvent: SingleLiveEvent<Int> = SingleLiveEvent()
    }
}