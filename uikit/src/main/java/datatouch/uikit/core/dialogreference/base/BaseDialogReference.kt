package datatouch.uikit.core.dialogreference.base

import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import datatouch.uikit.core.dialogreference.interfaces.IDialogInstanceProvider
import datatouch.uikit.core.dialogreference.interfaces.IDialogReference

internal abstract class BaseDialogReference<T : DialogFragment>(
    private var fragmentManager: FragmentManager?,
    private var instanceProvider: IDialogInstanceProvider<T>?
) : IDialogReference<T> {

    override fun show() {
        showInternal()
        drop()
    }

    override fun showThenDo(act: (T) -> Unit) {
        if (showInternal()) {
            execAction(act)
        }
        drop()
    }

    override fun dismiss() {
        dismissInternal()
        drop()
    }

    override fun dismissThenDo(act: (T) -> Unit) {
        if (dismissInternal()) {
            execAction(act)
        }
        drop()
    }

    override fun <V> withInstanceDo(act: (T) -> V): V? {
        val f = instanceProvider?.getOrCreateInstance(fragmentManager)
        val result = f?.let { act.invoke(it) }
        drop()
        return result
    }

    override fun isAdded(): Boolean {
        val f = instanceProvider?.getInstance(fragmentManager)
        val result = if (f != null) isAddedInternal(f) else false
        drop()
        return result
    }

    private fun isAddedInternal(f: T): Boolean {
        return (f.isAdded || instanceProvider?.isBusy() == true)
    }

    private fun dismissInternal(): Boolean {
        val f = instanceProvider?.getInstance(fragmentManager) ?: return false

        if (f.isAdded && instanceProvider?.isNotBusy() == true) {
            f.dismiss()
            setBusyFlag()
        }
        return true
    }

    private fun showInternal(): Boolean {
        val f = instanceProvider?.getOrCreateInstance(fragmentManager) ?: return false
        val fm = fragmentManager ?: return false

        if (!isAddedInternal(f)) {
            f.show(fm, instanceProvider?.provideTag())
            setBusyFlag()
        }
        return true
    }

    private fun setBusyFlag() {
        instanceProvider?.setBusyFlag(getParentRootView())
    }

    private fun execAction(act: (T) -> Unit) {
        val f = instanceProvider?.getInstance(fragmentManager) ?: return
        getParentRootView()?.post { act.invoke(f) }
    }

    protected open fun drop() {
        fragmentManager = null
        instanceProvider = null
    }

    protected abstract fun getParentRootView(): View?
}