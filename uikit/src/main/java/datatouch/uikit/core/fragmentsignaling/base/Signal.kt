package datatouch.uikit.core.fragmentsignaling.base

import datatouch.uikit.core.extensions.GenericExtensions.default
import datatouch.uikit.core.fragmentsignaling.interfaces.ISignal
import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotRetValAction
import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotRetValSuspendAction


@Suppress("FunctionName")
internal fun <R> Signal(
    slotId: SigSlotId,
    invokerName: String?,
    retValAction: ISlotRetValAction<R>? = null,
    slotParameters: Array<Any?>? = null
): ISignal = SignalImpl(slotId, invokerName, retValAction, slotParameters, false )

@Suppress("FunctionName")
internal fun <R> SignalSuspend(
    slotId: SigSlotId,
    invokerName: String?,
    retValAction: ISlotRetValSuspendAction<R>? = null,
    slotParameters: Array<Any?>? = null
): ISignal = SignalImpl(slotId, invokerName, retValAction, slotParameters, false )


@Suppress("FunctionName")
internal fun <R> SignalBlocking(
    slotId: SigSlotId,
    invokerName: String?,
    retValAction: ISlotRetValAction<R>? = null,
    slotParameters: Array<Any?>? = null
): ISignal = SignalImpl(slotId, invokerName, retValAction, slotParameters, true )


@Suppress("FunctionName")
internal fun <R> SignalBlockingSuspend(
    slotId: SigSlotId,
    invokerName: String?,
    retValAction: ISlotRetValSuspendAction<R>? = null,
    slotParameters: Array<Any?>? = null
): ISignal = SignalImpl(slotId, invokerName, retValAction, slotParameters, true )



private class SignalImpl<R> : ISignal {

    private var slotId: SigSlotId
    private var invokerName: String?
    private var retValAction: ISlotRetValAction<R>?
    private var retValSuspendAction: ISlotRetValSuspendAction<R>?
    private var slotParameters: Array<Any?>?
    private val isBlocking: Boolean

    constructor(slotId1: SigSlotId,
                invokerName: String?,
                retValAction: ISlotRetValAction<R>? = null,
                slotParameters: Array<Any?>? = null,
                isBlocking: Boolean = false) {

        this.slotId = slotId1
        this.invokerName = invokerName
        this.retValAction = retValAction
        this.retValSuspendAction = null
        this.slotParameters = slotParameters
        this.isBlocking = isBlocking
    }

    constructor(slotId1: SigSlotId,
                invokerName: String?,
                retValSuspendAction: ISlotRetValSuspendAction<R>? = null,
                slotParameters: Array<Any?>? = null,
                isBlocking: Boolean = false) {

        this.slotId = slotId1
        this.invokerName = invokerName
        this.retValAction = null
        this.retValSuspendAction = retValSuspendAction
        this.slotParameters = slotParameters
        this.isBlocking = isBlocking
    }

    override fun getSlotId(): SigSlotId = slotId

    override fun getSlotParameters(): Array<Any?>? {
        return slotParameters
    }

    override fun isNotBelongsToConsumer(consumerName: String): Boolean {
        return slotId.isNotBelongsToConsumer(consumerName)
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun execRetValAction(returnValue: Any?) {
        // Call only one of available actions
        if (retValAction != null) {
            runCatching { retValAction?.invoke(returnValue as R) }
            return
        }

        if (retValSuspendAction != null) {
            runCatching { retValSuspendAction?.invoke(returnValue as R) }
        }
    }

    override fun getInvokerName(): String {
        return invokerName.default("")
    }

    override fun isBlocking(): Boolean = isBlocking

    override fun isEmptySlotId(): Boolean = slotId.isEmpty()

    override fun drop() {
        slotParameters?.fill(null)
        slotParameters = null
        retValAction = null
        retValSuspendAction = null
        invokerName = null
    }

    override fun toString(): String {
        return "Signal:$slotId"
    }
}
