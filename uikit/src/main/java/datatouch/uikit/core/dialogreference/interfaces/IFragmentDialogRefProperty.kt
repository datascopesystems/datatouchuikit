package datatouch.uikit.core.dialogreference.interfaces

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import kotlin.reflect.KProperty

interface IFragmentDialogRefProperty<T : DialogFragment> {
    operator fun getValue(thisRef: Fragment, property: KProperty<*>): IDialogReference<T>
}