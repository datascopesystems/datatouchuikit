package datatouch.uikit.core.fragmentsignaling.interfaces

interface ISignal : ISlotIdOwner {
    fun getSlotParameters(): Array<Any?>?
    suspend fun execRetValAction(returnValue: Any?)
    fun isNotBelongsToConsumer(consumerName: String): Boolean
    fun getInvokerName(): String
    fun isBlocking(): Boolean
    fun isEmptySlotId(): Boolean
}