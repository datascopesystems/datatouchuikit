package datatouch.uikit.core.fragmentsignaling.variation.slots

import datatouch.uikit.core.fragmentsignaling.base.SigSlot
import datatouch.uikit.core.fragmentsignaling.base.SigSlotId
import datatouch.uikit.core.fragmentsignaling.base.SlotExecContext
import datatouch.uikit.core.fragmentsignaling.base.SlotExecResult
import datatouch.uikit.core.fragmentsignaling.exceptions.SlotExecArgsException
import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotRetValAction
import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotRetValSuspendAction
import datatouch.uikit.core.fragmentsignaling.variation.sigfun.SigFun1
import datatouch.uikit.core.fragmentsignaling.variation.sigfun.SigFunVoid1
import kotlinx.coroutines.runBlocking

internal class SigSlot1<A, R>(slotId: SigSlotId, slotContext: SlotExecContext, call: SigSlotCall1<A, R>)
    : SigSlot<SigSlotCall1<A, R>>(slotId, slotContext, call), SigFun1<A, R>, SigFunVoid1<A> {

    @Suppress("UNCHECKED_CAST")
    override suspend fun execute(args: Array<Any?>?): SlotExecResult {
        if (args?.size != 1) {
            return SlotExecResult.error(SlotExecArgsException())
        }

        return runCatching {
            val a = args.component1()
            call?.invoke(a as A)
        }.map {
            SlotExecResult.success(it)
        }.getOrElse { SlotExecResult.error(it) }
    }

    /** Invoke slot action; Avoid to call it directly */
    @Suppress("UNCHECKED_CAST")
    override fun invoke(a: A, act: ISlotRetValAction<R>) {
        val result = runBlocking { call?.invoke(a) }
        runCatching { act.invoke(result as R) }
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invoke(a: A) {
        runBlocking { call?.invoke(a) }
    }

    /** Invoke slot action; Avoid to call it directly */
    @Suppress("UNCHECKED_CAST")
    override fun invokeSuspend(a: A, act: ISlotRetValSuspendAction<R>) {
        runBlocking {
            val result = call?.invoke(a)
            runCatching { act.invoke(result as R) }
        }
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invokeBlocking(a: A, act: ISlotRetValAction<R>) {
        invoke(a, act)
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invokeBlocking(a: A) {
        invoke(a)
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invokeBlockingSuspend(a: A, act: ISlotRetValSuspendAction<R>) {
        invokeSuspend(a, act)
    }
}