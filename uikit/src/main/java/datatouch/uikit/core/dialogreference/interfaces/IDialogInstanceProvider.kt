package datatouch.uikit.core.dialogreference.interfaces

import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

internal interface IDialogInstanceProvider<T : DialogFragment> {
    fun getOrCreateInstance(fragmentManager: FragmentManager?): T?
    fun getInstance(fragmentManager: FragmentManager?): T?
    fun provideTag(): String?
    fun isBusy(): Boolean
    fun isNotBusy(): Boolean
    fun setBusyFlag(v: View?)
}