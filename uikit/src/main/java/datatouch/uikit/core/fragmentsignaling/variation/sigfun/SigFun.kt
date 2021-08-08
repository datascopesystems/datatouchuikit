package datatouch.uikit.core.fragmentsignaling.variation.sigfun

import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotRetValAction
import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotRetValSuspendAction


interface SigFun0<R> : ISignalFunction0 {
     operator fun invoke(act: ISlotRetValAction<R>)
     fun invokeSuspend(act: ISlotRetValSuspendAction<R>)

     fun invokeBlocking(act: ISlotRetValAction<R>)
     fun invokeBlockingSuspend(act: ISlotRetValSuspendAction<R>)
}

interface SigFun1<in A, R> : ISignalFunction1 {
     operator fun invoke(a: A, act: ISlotRetValAction<R>)
     fun invokeSuspend(a: A, act: ISlotRetValSuspendAction<R>)

     fun invokeBlocking(a: A, act: ISlotRetValAction<R>)
     fun invokeBlockingSuspend(a: A, act: ISlotRetValSuspendAction<R>)
}

interface SigFun2<in A, in B, R> : ISignalFunction2 {
     operator fun invoke(a: A, b: B, act: ISlotRetValAction<R>)
     fun invokeSuspend(a: A, b: B, act: ISlotRetValSuspendAction<R>)

     fun invokeBlocking(a: A, b: B, act: ISlotRetValAction<R>)
     fun invokeBlockingSuspend(a: A, b: B, act: ISlotRetValSuspendAction<R>)
}