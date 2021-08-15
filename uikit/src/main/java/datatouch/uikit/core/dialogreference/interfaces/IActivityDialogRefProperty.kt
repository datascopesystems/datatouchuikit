package datatouch.uikit.core.dialogreference.interfaces

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import kotlin.reflect.KProperty

interface IActivityDialogRefProperty<T : DialogFragment> {
    operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): IDialogReference<T>
}