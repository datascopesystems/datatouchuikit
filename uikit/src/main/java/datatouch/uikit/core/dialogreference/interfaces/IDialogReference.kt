package datatouch.uikit.core.dialogreference.interfaces

import androidx.fragment.app.DialogFragment

interface IDialogReference<T : DialogFragment> {
    fun show()
    fun showThenDo(act: (T) -> Unit)

    fun dismiss()
    fun dismissThenDo(act: (T) -> Unit)

    fun <V> withInstanceDo(act: (T) -> V): V?

    fun isAdded(): Boolean
}