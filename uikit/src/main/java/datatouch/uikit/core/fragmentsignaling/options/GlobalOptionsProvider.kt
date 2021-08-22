package datatouch.uikit.core.fragmentsignaling.options

import datatouch.uikit.core.fragmentsignaling.interfaces.ISigFactoryOptions

internal object GlobalOptionsProvider {
    private val options: ISigFactoryOptions = SigFactoryOptionsImpl()

    fun get(): ISigFactoryOptions = options
}