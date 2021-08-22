package datatouch.uikit.core.fragmentsignaling.options

import datatouch.uikit.core.fragmentsignaling.interfaces.ISigFactoryOptions

internal class SigFactoryOptionsImpl : ISigFactoryOptions {

    override fun copy(): SigFactoryOptionsImpl {
        return SigFactoryOptionsImpl()
    }
}