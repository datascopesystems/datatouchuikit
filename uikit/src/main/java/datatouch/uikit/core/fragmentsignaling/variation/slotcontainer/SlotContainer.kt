package datatouch.uikit.core.fragmentsignaling.variation.slotcontainer

import datatouch.uikit.core.fragmentsignaling.base.SigSlotProperty
import datatouch.uikit.core.fragmentsignaling.base.SlotExecContext
import datatouch.uikit.core.fragmentsignaling.consumer.SlotExecutableContainer
import datatouch.uikit.core.fragmentsignaling.variation.sigfun.*
import datatouch.uikit.core.fragmentsignaling.variation.slots.*
import datatouch.uikit.core.fragmentsignaling.variation.slots.SigSlot0
import datatouch.uikit.core.fragmentsignaling.variation.slots.SigSlot1
import datatouch.uikit.core.fragmentsignaling.variation.slots.SigSlot2


abstract class SlotContainer : SlotExecutableContainer() {

    @Suppress("UNCHECKED_CAST")
    internal fun <R, F : ISignalFunction0> addSlot(slotContext: SlotExecContext, call: SigSlotCall0<R>)
    : SigSlotProperty<F> {

        val s = addExecutable(SigSlot0(createSlotId(), slotContext, call))
        return SigSlotProperty(s as F)
    }

    @Suppress("UNCHECKED_CAST")
    internal fun <A, R, F : ISignalFunction1> addSlot(slotContext: SlotExecContext, call: SigSlotCall1<A, R>)
    : SigSlotProperty<F> {

        val s = addExecutable(SigSlot1(createSlotId(), slotContext, call))
        return SigSlotProperty(s as F)
    }

    @Suppress("UNCHECKED_CAST")
    internal fun <A, B, R, F : ISignalFunction2> addSlot(slotContext: SlotExecContext, call: SigSlotCall2<A, B, R>)
    : SigSlotProperty<F> {

        val s = addExecutable(SigSlot2(createSlotId(), slotContext, call))
        return SigSlotProperty(s as F)
    }
}