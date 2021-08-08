package datatouch.uikit.core.fragmentsignaling.base

import datatouch.uikit.core.fragmentsignaling.exceptions.SlotNoActionsException
import datatouch.uikit.core.fragmentsignaling.interfaces.ISigSlotCall

internal abstract class SigSlotCall<R> : ISigSlotCall {

    private var slotId = SigSlotId.createEmpty()

    override fun assignSlotId(slotId: SigSlotId) {
        this.slotId = slotId
    }

    protected fun assertHasAnyAction(actA: Any?, actB: Any?) {
        if (actA == null && actB == null) {
            throw SlotNoActionsException(slotId)
        }
    }
}