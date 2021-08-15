package datatouch.uikit.core.dialogreference.property

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import datatouch.uikit.core.dialogreference.base.BaseDialogReferenceProperty
import datatouch.uikit.core.dialogreference.interfaces.IActivityDialogRefProperty
import datatouch.uikit.core.dialogreference.interfaces.IDialogReference
import datatouch.uikit.core.dialogreference.reference.ActivityDialogRef
import datatouch.uikit.core.windowargs.argsmap.ActivityArgsMap
import datatouch.uikit.core.windowargs.argsmap.ArgsMap
import kotlin.reflect.KProperty

internal class ActivityDialogRefProperty <T : DialogFragment>(
    parentActivity: AppCompatActivity,
    instanceCreator: () -> T
) : BaseDialogReferenceProperty<T>(instanceCreator), IActivityDialogRefProperty<T> {

    init { initActivityArgsIntent(parentActivity) }

    private fun initActivityArgsIntent(parentActivity: AppCompatActivity) {
        ArgsMap.getOrCreateArgsIntent(parentActivity)
    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): IDialogReference<T> {
        assignTag(thisRef, property)
        return ActivityDialogRef(thisRef, this)
    }

    private fun assignTag(thisRef: AppCompatActivity, property: KProperty<*>) {
        if (tag == null) {
            tag = restoreOrCreateTag(property.name, ActivityArgsMap(thisRef))
        }
    }
}