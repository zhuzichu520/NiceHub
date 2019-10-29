package com.zhuzichu.android.widget.dialog.loading

import android.content.Context
import java.lang.ref.WeakReference

object LoadingMaker {

    private var sProgressDialogRef: WeakReference<LoadingDialog>? = null

    val isShowing: Boolean
        get() {
            val dialog = dialog
            return dialog != null && dialog.isShowing
        }

    private val dialog: LoadingDialog?
        get() = if (sProgressDialogRef == null) null else sProgressDialogRef!!.get()


    fun showLoadingDialog(context: Context, canCancelable: Boolean = false): LoadingDialog {
        var dialog = dialog
        if (dialog != null && dialog.context !== context) {
            dismissLodingDialog()
            dialog = null
        }
        if (dialog == null) {
            dialog = LoadingDialog(context)
            sProgressDialogRef = WeakReference(dialog)
        }
        dialog.setCancelable(canCancelable)
        dialog.show()
        return dialog
    }

    fun dismissLodingDialog() {
        val dialog = dialog ?: return
        sProgressDialogRef!!.clear()
        if (dialog.isShowing) {
            try {
                dialog.dismiss()
            } catch (e: Exception) {
            }
        }
    }
}