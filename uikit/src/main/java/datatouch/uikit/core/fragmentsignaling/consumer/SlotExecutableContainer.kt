package datatouch.uikit.core.fragmentsignaling.consumer

import androidx.lifecycle.LifecycleOwner
import datatouch.uikit.core.extensions.GenericExtensions.default
import datatouch.uikit.core.fragmentsignaling.base.SigSlotId
import datatouch.uikit.core.fragmentsignaling.base.SlotExecContext
import datatouch.uikit.core.fragmentsignaling.interfaces.IDropableSignal
import datatouch.uikit.core.fragmentsignaling.interfaces.ISigFactoryOptions
import datatouch.uikit.core.fragmentsignaling.interfaces.ISigSlotExecutable
import datatouch.uikit.core.fragmentsignaling.interfaces.ISignal
import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


abstract class SlotExecutableContainer(
    private val opt: ISigFactoryOptions
) : IDropableSignal {

    private var slots: MutableList<ISigSlotExecutable>? = null

    private var consumerName = ""

    protected fun configureConsumerName(owner: LifecycleOwner) {
        consumerName = owner.javaClass.name
    }

    private fun addExecutableInternal(executable: ISigSlotExecutable) {
        if (slots == null) {
            slots = mutableListOf()
        }

        slots?.add(executable)
    }

    protected fun <T: ISigSlotExecutable> addExecutable(invokable: T): T {
        addExecutableInternal(invokable)
        return invokable
    }

    protected suspend fun consumeSignal(signal: ISignal) {
        if (signal.isNotBelongsToConsumer(consumerName)) {
            return
        }

        val slot = getSlotForSignal(signal) ?: return

        when (signal.isBlocking()) {
            true -> consumeSignalWithSlotBlocking(slot, signal)
            else -> consumeSignalWithSlotAsync(slot, signal)
        }
    }

    private suspend fun consumeSignalWithSlotBlocking(sigSlot: ISigSlotExecutable, signal: ISignal) {
        when (sigSlot.getSlotContext()) {
            // NO need to switch context, we already in Dispatchers.Main.immediate
            SlotExecContext.Main -> executeSlot(sigSlot, signal)
            // Switch context to IO
            SlotExecContext.IO -> executeSlotWithContext(Dispatchers.IO, sigSlot, signal)
        }
    }

    private fun consumeSignalWithSlotAsync(sigSlot: ISigSlotExecutable, signal: ISignal) {
        when (sigSlot.getSlotContext()) {
            SlotExecContext.Main -> launch(Dispatchers.Main) { executeSlot(sigSlot, signal) }
            SlotExecContext.IO -> launch(Dispatchers.IO) { executeSlot(sigSlot, signal) }
        }
    }

    private suspend fun executeSlot(sigSlot: ISigSlotExecutable, signal: ISignal) {
        val retValResult = sigSlot.execute(signal.getSlotParameters())
        if (retValResult.isSuccess()) {
            signal.execRetValAction(retValResult.value)
        }
        signal.drop()
        retValResult.drop()
    }

    private suspend fun executeSlotWithContext(context: CoroutineContext, sigSlot: ISigSlotExecutable, signal: ISignal) {
        when (isInSameContext(context)) {
            true -> executeSlot(sigSlot, signal)
            else -> withContext(context) {
                executeSlot(sigSlot, signal)
            }
        }
    }

    private fun getSlotForSignal(signal: ISignal): ISigSlotExecutable? {
        return slots?.firstOrNull { it.isSlotNumberEquals(signal.getSlotId()) }
    }

    override fun drop() {
        slots?.forEach { it.drop() }
        slots?.clear()
        slots = null
    }

    protected fun createSlotId() = SigSlotId(consumerName, getSize())

    protected fun getSize(): Int = slots?.size.default(0)

    private suspend fun isInSameContext(otherCtx: CoroutineContext): Boolean {
        val currCtx = currentCoroutineContext()
        return currCtx[ContinuationInterceptor] == otherCtx[ContinuationInterceptor]
    }

    protected abstract fun getCoroutineScope(): CoroutineScope?

    private fun launch(context: CoroutineContext = EmptyCoroutineContext,
                       block: suspend CoroutineScope.() -> Unit) {
        getCoroutineScope()?.launch(context = context, block = block)
    }
}