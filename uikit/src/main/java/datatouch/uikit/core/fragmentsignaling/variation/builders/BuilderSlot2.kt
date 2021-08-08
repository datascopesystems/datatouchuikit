package datatouch.uikit.core.fragmentsignaling.variation.builders

import datatouch.uikit.core.fragmentsignaling.base.BuilderSlotProperty
import datatouch.uikit.core.fragmentsignaling.base.SigSlotProperty
import datatouch.uikit.core.fragmentsignaling.base.SlotExecContext
import datatouch.uikit.core.fragmentsignaling.variation.sigfun.*
import datatouch.uikit.core.fragmentsignaling.variation.slotcontainer.SlotContainer
import datatouch.uikit.core.fragmentsignaling.variation.slots.*


class BuilderSlot2<A, B>(slotContainer: SlotContainer) : BuilderSlotProperty(slotContainer) {

    /**
     *  Create slot for signal with 2 params and no return value.
     *
     *  Runs in Dispatchers.Main context.
     *
     *  A, B - param types;
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFunVoid2 type
     */
    fun of(act: ISigSlotAction2<A, B, Unit>): SigSlotProperty<SigFunVoid2<A, B>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall2(act))
    }

    /**
     *  Create suspend slot for signal with 2 params and no return value
     *
     *  Runs in Dispatchers.Main context.
     *
     *  A, B - param types;
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFunVoid2 type
     */
    fun ofSuspend(act: ISigSlotSuspendAction2<A, B, Unit>): SigSlotProperty<SigFunVoid2<A, B>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall2(act))
    }

    /**
     *  Create slot for signal with 2 params and no return value.
     *
     *  Runs in Dispatchers.IO context.
     *
     *  A, B - param types;
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFunVoid2 type
     */

    fun io(act: ISigSlotAction2<A, B, Unit>): SigSlotProperty<SigFunVoid2<A, B>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall2(act))
    }

    /**
     *  Create suspend slot for signal with 2 params and no return value
     *
     *  Runs in Dispatchers.IO context.
     *
     *  A, B - param types;
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFunVoid2 type
     */
    fun ioSuspend(act: ISigSlotSuspendAction2<A, B, Unit>): SigSlotProperty<SigFunVoid2<A, B>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall2(act))
    }


    // The following methods is just overloaded versions
    // So we can use it without generic argument: builder.of { some code }
    // or with generic argument: builder.of<String> { some code }


    /**
     *  Create slot for signal with 2 params and with return value.
     *
     *  Runs in Dispatchers.Main context.
     *
     *  A, B - param types;
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFun2<A, B, R> type
     */
    fun <R> of(ignore: R? = null, act: ISigSlotAction2<A, B, R>): SigSlotProperty<SigFun2<A, B, R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall2(act))
    }

    /**
     *  Create suspend slot for signal with 2 params and with return value
     *
     *  Runs in Dispatchers.Main context.
     *
     *  A, B - param types;
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFun2<A, B, R> type
     */
    fun <R> ofSuspend(ignore: R? = null, act: ISigSlotSuspendAction2<A, B, R>): SigSlotProperty<SigFun2<A, B, R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall2(act))
    }

    /**
     *  Create slot for signal with 2 params and with return value.
     *
     *  Runs in Dispatchers.IO context.
     *
     *  A, B - param types;
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFun2<A, B, R> type
     */
    fun <R> io(ignore: R? = null, act: ISigSlotAction2<A, B, R>): SigSlotProperty<SigFun2<A, B, R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall2(act))
    }

    /**
     *  Create suspend slot for signal with 2 params and with return value
     *
     *  Runs in Dispatchers.IO context.
     *
     *  A, B - param types;
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFun2<A, B, R> type
     */
    fun <R> ioSuspend(ignore: R? = null, act: ISigSlotSuspendAction2<A, B, R>): SigSlotProperty<SigFun2<A, B, R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall2(act))
    }
}