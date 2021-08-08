package datatouch.uikit.core.fragmentsignaling.variation.builders

import datatouch.uikit.core.fragmentsignaling.base.BuilderSlotProperty
import datatouch.uikit.core.fragmentsignaling.base.SigSlotProperty
import datatouch.uikit.core.fragmentsignaling.base.SlotExecContext
import datatouch.uikit.core.fragmentsignaling.variation.sigfun.SigFun1
import datatouch.uikit.core.fragmentsignaling.variation.sigfun.SigFunVoid1
import datatouch.uikit.core.fragmentsignaling.variation.slotcontainer.SlotContainer
import datatouch.uikit.core.fragmentsignaling.variation.slots.ISigSlotAction1
import datatouch.uikit.core.fragmentsignaling.variation.slots.ISigSlotSuspendAction1
import datatouch.uikit.core.fragmentsignaling.variation.slots.SigSlotCall1

class BuilderSlot1<A>(slotContainer: SlotContainer) : BuilderSlotProperty(slotContainer) {

    /**
     *  Create slot for signal with 1 param and no return value.
     *
     *  Runs in Dispatchers.Main context.
     *
     *  A - param type;
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFunVoid1 type
     */
    fun of(act: ISigSlotAction1<A, Unit>): SigSlotProperty<SigFunVoid1<A>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall1(act))
    }

    /**
     *  Create suspend slot for signal with 1 param and no return value.
     *
     *  Runs in Dispatchers.Main context.
     *
     *  A - param type;
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFunVoid1 type
     */
    fun ofSuspend(act: ISigSlotSuspendAction1<A, Unit>): SigSlotProperty<SigFunVoid1<A>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall1(act))
    }

    /**
     *  Create slot for signal with 1 param and no return value.
     *
     *  Runs in Dispatchers.IO context.
     *
     *  A - param type;
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFunVoid1 type
     */
    fun io(act: ISigSlotAction1<A, Unit>): SigSlotProperty<SigFunVoid1<A>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall1(act))
    }

    /**
     *  Create suspend slot for signal with 1 param and no return value.
     *
     *  Runs in Dispatchers.IO context.
     *
     *  A - param type;
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFunVoid1 type
     */
    fun ioSuspend(act: ISigSlotSuspendAction1<A, Unit>): SigSlotProperty<SigFunVoid1<A>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall1(act))
    }


    // The following methods is just overloaded versions
    // So we can use it without generic argument: builder.of { some code }
    // or with generic argument: builder.of<String> { some code }


    /**
     *  Create slot for signal with 1 param and with return value.
     *
     *  Runs in Dispatchers.Main context.
     *
     *  A - param type;
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFun1<A, R> type
     */
    fun <R> of(ignore: R? = null, act: ISigSlotAction1<A, R>): SigSlotProperty<SigFun1<A, R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall1(act))
    }

    /**
     *  Create suspend slot for signal with 1 param and with return value.
     *
     *  Runs in Dispatchers.Main context.
     *
     *  A - param type;
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFun1<A, R> type
     */
    fun <R> ofSuspend(ignore: R? = null, act: ISigSlotSuspendAction1<A, R>): SigSlotProperty<SigFun1<A, R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.Main, SigSlotCall1(act))
    }

    /**
     *  Create slot for signal with 1 param and with return value.
     *
     *  Runs in Dispatchers.IO context.
     *
     *  A - param type;
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - lambda or function reference
     *  @return readonly property delegate for SigFun1<A, R> type
     */
    fun <R> io(ignore: R? = null, act: ISigSlotAction1<A, R>): SigSlotProperty<SigFun1<A, R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall1(act))
    }

    /**
     *  Create suspend slot for signal with 1 param and with return value.
     *
     *  Runs in Dispatchers.IO context.
     *
     *  A - param type;
     *  R - return value type
     *  @param ignore must be ignored; it is used for overload only
     *  @param act signal handler - suspend lambda or function reference
     *  @return readonly property delegate for SigFun1<A, R> type
     */
    fun <R> ioSuspend(ignore: R? = null, act: ISigSlotSuspendAction1<A, R>): SigSlotProperty<SigFun1<A, R>> {
        return getSlotContainerOnce().addSlot(SlotExecContext.IO, SigSlotCall1(act))
    }
}