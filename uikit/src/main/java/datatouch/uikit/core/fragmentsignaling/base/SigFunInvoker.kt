package datatouch.uikit.core.fragmentsignaling.base

import datatouch.uikit.core.fragmentsignaling.viewmodel.SignalSharedViewModel
import datatouch.uikit.core.fragmentsignaling.interfaces.ISignal
import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotIdOwner

internal abstract class SigFunInvoker(private val slotId: SigSlotId,
                                      protected var invokerName: String?,
                                      private var vm: SignalSharedViewModel?): ISlotIdOwner {

    override fun getSlotId(): SigSlotId = slotId

    protected fun emitSignal(signal: ISignal) {
        when (signal.isBlocking()) {
            true -> vm?.emitSignalBlocking(signal)
            else -> vm?.emitSignal(signal)
        }
        drop()
    }

    override fun drop() {
        vm = null
        invokerName = null
    }
}