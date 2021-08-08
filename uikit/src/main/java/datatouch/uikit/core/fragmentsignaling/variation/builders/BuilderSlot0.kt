package datatouch.uikit.core.fragmentsignaling.variation.builders

import datatouch.uikit.core.fragmentsignaling.base.BuilderSlotProperty
import datatouch.uikit.core.fragmentsignaling.base.SigSlotProperty
import datatouch.uikit.core.fragmentsignaling.base.SlotExecContext
import datatouch.uikit.core.fragmentsignaling.variation.sigfun.SigFun0
import datatouch.uikit.core.fragmentsignaling.variation.sigfun.SigFunVoid0
import datatouch.uikit.core.fragmentsignaling.variation.slotcontainer.SlotContainer
import datatouch.uikit.core.fragmentsignaling.variation.slots.ISigSlotAction0
import datatouch.uikit.core.fragmentsignaling.variation.slots.ISigSlotSuspendAction0
import datatouch.uikit.core.fragmentsignaling.variation.slots.SigSlotCall0

class BuilderSlot0(slotContainer: SlotContainer) : BuilderSlotProperty(slotContainer) {

    /**
     *  Create slot for signal with no params and no return value.
     *
     *  Runs in Dispatchers.Main context.
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFunVoid0 type
     */
    fun of(act: ISigSlotAction0<Unit>): SigSlotProperty<SigFunVoid0> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall0(act))
    }

    /**
     *  Create suspend slot for signal with no params and no return value.
     *
     *  Runs in Dispatchers.Main context.
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFunVoid0 type
     */
    fun ofSuspend(act: ISigSlotSuspendAction0<Unit>): SigSlotProperty<SigFunVoid0> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall0(act))
    }

    /**
     *  Create slot for signal with no params and no return value.
     *
     *  Runs in Dispatchers.IO context.
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFunVoid0 type
     */
    fun io(act: ISigSlotAction0<Unit>): SigSlotProperty<SigFunVoid0> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall0(act))
    }

    /**
     *  Create suspend slot for signal with no params and no return value.
     *
     *  Runs in Dispatchers.IO context.
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFunVoid0 type
     */
    fun ioSuspend(act: ISigSlotSuspendAction0<Unit>): SigSlotProperty<SigFunVoid0> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall0(act))
    }


    // The following methods is just overloaded versions
    // So we can use it without generic argument: builder.of { some code }
    // or with generic argument: builder.of<String> { some code }


    /**
     *  Create slot for signal with no params and with return value.
     *
     *  Runs in Dispatchers.Main context.
     *
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFun0<R> type
     */
    fun <R> of(ignore: R? = null, act: ISigSlotAction0<R>): SigSlotProperty<SigFun0<R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall0(act))
    }

    /**
     *  Create suspend slot for signal with no params and with return value.
     *
     *  Runs in Dispatchers.Main context.
     *
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFun0<R> type
     */
    fun <R> ofSuspend(ignore: R? = null, act: ISigSlotSuspendAction0<R>): SigSlotProperty<SigFun0<R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall0(act))
    }

    /**
     *  Create slot for signal with no params and with return value.
     *
     *  Runs in Dispatchers.IO context.
     *
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFun0<R> type
     */
    fun <R> io(ignore: R? = null, act: ISigSlotAction0<R>): SigSlotProperty<SigFun0<R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall0(act))
    }

    /**
     *  Create suspend slot for signal with no params and with return value.
     *
     *  Runs in Dispatchers.IO context.
     *
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFun0<R> type
     */
    fun <R> ioSuspend(ignore: R? = null, act: ISigSlotSuspendAction0<R>): SigSlotProperty<SigFun0<R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall0(act))
    }
}