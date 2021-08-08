package datatouch.uikit.core.fragmentsignaling.variation.slots

import datatouch.uikit.core.fragmentsignaling.base.SigSlotCall

internal class SigSlotCall0<R> : SigSlotCall<R> {

    private var regularAct: ISigSlotAction0<R>? = null
    private var suspendAct: ISigSlotSuspendAction0<R>? = null

    constructor(act: ISigSlotAction0<R>) {
        regularAct = act
    }

    constructor(act: ISigSlotSuspendAction0<R>) {
        suspendAct = act
    }

    suspend operator fun invoke(): R {
        assertHasAnyAction(regularAct, suspendAct)
        return when (regularAct != null) {
            true -> regularAct!!.invoke()
            else -> suspendAct!!.invoke()
        }
    }

    override fun drop() {
        regularAct = null
        suspendAct = null
    }
}


internal class SigSlotCall1<A, R> : SigSlotCall<R> {

    private var regularAct : ISigSlotAction1<A, R>? = null
    private var suspendAct : ISigSlotSuspendAction1<A, R>? = null

    constructor(act: ISigSlotAction1<A, R>) {
        regularAct = act
    }

    constructor(act: ISigSlotSuspendAction1<A, R>) {
        suspendAct = act
    }

    suspend operator fun invoke(a: A): R {
        assertHasAnyAction(regularAct, suspendAct)
        return when (regularAct != null) {
            true -> regularAct!!.invoke(a)
            else -> suspendAct!!.invoke(a)
        }
    }

    override fun drop() {
        regularAct = null
        suspendAct = null
    }
}


internal class SigSlotCall2<A, B, R> : SigSlotCall<R> {

    private var regularAct : ISigSlotAction2<A, B, R>? = null
    private var suspendAct : ISigSlotSuspendAction2<A, B, R>? = null

    constructor(act: ISigSlotAction2<A, B, R>) {
        regularAct = act
    }

    constructor(act: ISigSlotSuspendAction2<A, B, R>) {
        suspendAct = act
    }

    suspend operator fun invoke(a: A, b: B): R {
        assertHasAnyAction(regularAct, suspendAct)
        return when (regularAct != null) {
            true -> regularAct!!.invoke(a, b)
            else -> suspendAct!!.invoke(a, b)
        }
    }

    override fun drop() {
        regularAct = null
        suspendAct = null
    }
}