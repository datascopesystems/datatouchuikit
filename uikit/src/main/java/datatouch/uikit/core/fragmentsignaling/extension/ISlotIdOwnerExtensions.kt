package datatouch.uikit.core.fragmentsignaling.extension

import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotIdOwner
import kotlin.reflect.KProperty

internal fun ISlotIdOwner.assignSlotNameFromProperty(srcProperty: KProperty<*>) {
    if (getSlotId().hasSlotName()) {
        return
    }

    getSlotId().assignSlotName(srcProperty.name)
}