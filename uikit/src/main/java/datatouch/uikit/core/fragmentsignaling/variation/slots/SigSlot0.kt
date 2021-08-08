package datatouch.uikit.core.fragmentsignaling.variation.slots

import datatouch.uikit.core.fragmentsignaling.base.SigSlot
import datatouch.uikit.core.fragmentsignaling.base.SigSlotId
import datatouch.uikit.core.fragmentsignaling.base.SlotExecContext
import datatouch.uikit.core.fragmentsignaling.base.SlotExecResult
import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotRetValAction
import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotRetValSuspendAction
import datatouch.uikit.core.fragmentsignaling.variation.sigfun.SigFun0
import datatouch.uikit.core.fragmentsignaling.variation.sigfun.SigFunVoid0
import kotlinx.coroutines.runBlocking

internal class SigSlot0<R>(slotId: SigSlotId, slotContext: SlotExecContext, call: SigSlotCall0<R>)
    : SigSlot<SigSlotCall0<R>>(slotId, slotContext, call), SigFun0<R>, SigFunVoid0 {

    override suspend fun execute(args: Array<Any?>?): SlotExecResult {
        return runCatching { call?.invoke() }
            .map { SlotExecResult.success(it) }
            .getOrElse { SlotExecResult.error(it) }
    }

    /** Invoke slot action; Avoid to call it directly */
    @Suppress("UNCHECKED_CAST")
    override fun invoke(act: ISlotRetValAction<R>) {
        val result = runBlocking { call?.invoke() }
        runCatching { act.invoke(result as R) }
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invoke() {
        runBlocking { call?.invoke() }
    }

    /** Invoke slot action; Avoid to call it directly */
    @Suppress("UNCHECKED_CAST")
    override fun invokeSuspend(act: ISlotRetValSuspendAction<R>) {
        runBlocking {
            val result = call?.invoke()
            runCatching { act.invoke(result as R) }
        }
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invokeBlocking() {
        invoke()
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invokeBlocking(act: ISlotRetValAction<R>) {
        invoke(act)
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invokeBlockingSuspend(act: ISlotRetValSuspendAction<R>) {
        invokeSuspend(act)
    }
}