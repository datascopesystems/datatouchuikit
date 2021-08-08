package datatouch.uikit.core.fragmentsignaling.variation.slots


import datatouch.uikit.core.fragmentsignaling.base.SigSlot
import datatouch.uikit.core.fragmentsignaling.base.SigSlotId
import datatouch.uikit.core.fragmentsignaling.base.SlotExecContext
import datatouch.uikit.core.fragmentsignaling.base.SlotExecResult
import datatouch.uikit.core.fragmentsignaling.exceptions.SlotExecArgsException
import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotRetValAction
import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotRetValSuspendAction
import datatouch.uikit.core.fragmentsignaling.variation.sigfun.*
import kotlinx.coroutines.runBlocking


internal class SigSlot2<A, B, R>(slotId: SigSlotId, slotContext: SlotExecContext, call: SigSlotCall2<A, B, R>)
    : SigSlot<SigSlotCall2<A, B, R>>(slotId, slotContext, call), SigFun2<A, B, R>, SigFunVoid2<A, B> {

    @Suppress("UNCHECKED_CAST")
    override suspend fun execute(args: Array<Any?>?): SlotExecResult {
        if (args?.size != 2) {
            return SlotExecResult.error(SlotExecArgsException())
        }

        return runCatching {
            val a = args.component1()
            val b = args.component2()
            call?.invoke(a as A, b as B)
        }.map {
            SlotExecResult.success(it)
        }.getOrElse { SlotExecResult.error(it) }
    }

    /** Invoke slot action; Avoid to call it directly */
    @Suppress("UNCHECKED_CAST")
    override fun invoke(a: A, b: B, act: ISlotRetValAction<R>) {
        val result = runBlocking { call?.invoke(a, b) }
        runCatching { act.invoke(result as R) }
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invoke(a: A, b: B) {
        runBlocking { call?.invoke(a, b) }
    }

    /** Invoke slot action; Avoid to call it directly */
    @Suppress("UNCHECKED_CAST")
    override fun invokeSuspend(a: A, b: B, act: ISlotRetValSuspendAction<R>) {
        runBlocking {
            val result = call?.invoke(a, b)
            runCatching { act.invoke(result as R) }
        }
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invokeBlocking(a: A, b: B, act: ISlotRetValAction<R>) {
        invoke(a, b, act)
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invokeBlocking(a: A, b: B) {
        invoke(a, b)
    }

    /** Invoke slot action; Avoid to call it directly */
    override fun invokeBlockingSuspend(a: A, b: B, act: ISlotRetValSuspendAction<R>) {
        invokeSuspend(a, b, act)
    }
}