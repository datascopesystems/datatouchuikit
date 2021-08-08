package datatouch.uikit.core.fragmentsignaling.base

import datatouch.uikit.core.fragmentsignaling.interfaces.ISigSlotCall
import datatouch.uikit.core.fragmentsignaling.interfaces.ISigSlotExecutable

internal abstract class SigSlot<Call : ISigSlotCall>(private val slotId: SigSlotId,
                                                     private val slotContext: SlotExecContext,
                                                     protected var call: Call?)
: ISigSlotExecutable {

    init { call?.assignSlotId(slotId) }

    override fun getSlotId(): SigSlotId = slotId

    override fun getSlotContext(): SlotExecContext = slotContext

    override fun drop() {
        call?.drop()
        call = null
    }

    override fun isSlotNumberEquals(id: SigSlotId): Boolean {
        return slotId.isSlotNumberEquals(id)
    }
}