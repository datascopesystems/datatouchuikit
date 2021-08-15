package datatouch.uikit.core.dialogreference.property

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import datatouch.uikit.core.dialogreference.base.BaseDialogReferenceProperty
import datatouch.uikit.core.dialogreference.interfaces.IDialogReference
import datatouch.uikit.core.dialogreference.interfaces.IFragmentDialogRefProperty
import datatouch.uikit.core.dialogreference.reference.FragmentDialogRef
import datatouch.uikit.core.windowargs.argsmap.ArgsMap
import datatouch.uikit.core.windowargs.argsmap.FragmentArgsMap
import kotlin.reflect.KProperty

internal class FragmentDialogRefProperty<T : DialogFragment>(
    parentFragment: Fragment,
    instanceCreator: () -> T
) : BaseDialogReferenceProperty<T>(instanceCreator), IFragmentDialogRefProperty<T> {

    init { initFragmentArgsBundle(parentFragment) }

    private fun initFragmentArgsBundle(parentFragment: Fragment) {
        ArgsMap.getOrCreateArgsBundle(parentFragment)
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): IDialogReference<T> {
        assignTag(thisRef, property)
        return FragmentDialogRef(thisRef, this)
    }

    private fun assignTag(thisRef: Fragment, property: KProperty<*>) {
        if (tag == null) {
            tag = restoreOrCreateTag(property.name, FragmentArgsMap(thisRef))
        }
    }
}