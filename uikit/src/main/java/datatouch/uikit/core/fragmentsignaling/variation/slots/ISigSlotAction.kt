package datatouch.uikit.core.fragmentsignaling.variation.slots

fun interface ISigSlotAction0<out R> : Function<R> {
    operator fun invoke(): R
}

fun interface ISigSlotAction1<in A, out R> : Function<R> {
    operator fun invoke(a: A): R
}

fun interface ISigSlotAction2<in A, in B, out R> : Function<R> {
    operator fun invoke(a: A, b: B): R
}



typealias ISigSlotSuspendAction0<R> = suspend () -> R
typealias ISigSlotSuspendAction1<A, R> = suspend (A) -> R
typealias ISigSlotSuspendAction2<A, B, R> = suspend (A, B) -> R