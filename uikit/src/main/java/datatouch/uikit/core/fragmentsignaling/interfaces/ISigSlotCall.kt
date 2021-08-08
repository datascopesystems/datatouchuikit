package datatouch.uikit.core.fragmentsignaling.interfaces

import datatouch.uikit.core.fragmentsignaling.base.SigSlotId

interface ISigSlotCall : IDropableSignal {
    fun assignSlotId(slotId: SigSlotId)
}