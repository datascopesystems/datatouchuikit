package datatouch.uikit.core.fragmentsignaling.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import datatouch.uikit.core.fragmentsignaling.interfaces.ISigFunActivityProperty
import datatouch.uikit.core.fragmentsignaling.interfaces.ISigFunFragmentProperty
import datatouch.uikit.core.fragmentsignaling.viewmodel.SignalSharedViewModel
import datatouch.uikit.core.fragmentsignaling.interfaces.ISlotIdOwner
import datatouch.uikit.core.fragmentsignaling.viewmodel.SignalViewModelProvider
import datatouch.uikit.core.windowargs.argsmap.ActivityArgsMap
import datatouch.uikit.core.windowargs.argsmap.ArgsMap
import datatouch.uikit.core.windowargs.argsmap.FragmentArgsMap
import kotlin.reflect.KProperty

internal abstract class SigFunInvokerProperty<V : ISlotIdOwner>
    : ISigFunFragmentProperty<V>, ISigFunActivityProperty<V> {

    private var sigSlotId = SigSlotId.createEmpty()

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: V) {
        setSigSlotId(thisRef, property, value.getSlotId())
    }

    override fun setValue(thisRef: AppCompatActivity, property: KProperty<*>, value: V) {
        setSigSlotId(thisRef, property, value.getSlotId())
    }

    protected fun getSigSlotId(f: Fragment, property: KProperty<*>): SigSlotId {
        return when (sigSlotId.isNotEmpty() || ArgsMap.isEmptyArguments(f)) {
            true -> sigSlotId
            else -> getMappedSigSlotId(FragmentArgsMap(f), property)
        }
    }

    protected fun getSigSlotId(a: AppCompatActivity, property: KProperty<*>): SigSlotId {
        return when (sigSlotId.isNotEmpty() || ArgsMap.isEmptyArguments(a)) {
            true -> sigSlotId
            else -> getMappedSigSlotId(ActivityArgsMap(a), property)
        }
    }

    private fun setSigSlotId(f: Fragment, property: KProperty<*>, value: SigSlotId) {
        sigSlotId = value
        setMappedSigSlotId(FragmentArgsMap(f), property, value)
    }

    private fun setSigSlotId(f: AppCompatActivity, property: KProperty<*>, value: SigSlotId) {
        sigSlotId = value
        setMappedSigSlotId(ActivityArgsMap(f), property, value)
    }

    private fun getMappedSigSlotId(map: ArgsMap, property: KProperty<*>): SigSlotId {
        if (map.isNotExist(property.name)) {
            return sigSlotId
        }

        restoreSigSlotId(map, property)
        return sigSlotId
    }

    private fun setMappedSigSlotId(map: ArgsMap, property: KProperty<*>, value: SigSlotId) {
        map.set(property.name, value)
    }

    private fun restoreSigSlotId(map: ArgsMap, property: KProperty<*>) {
        val value = map.get(property.name) ?: return
        castToSigSlotId(value)
    }

    protected fun getSharedViewModel(fragment: Fragment): SignalSharedViewModel {
        return SignalViewModelProvider.of(fragment)
    }

    protected fun getSharedViewModel(activity: AppCompatActivity): SignalSharedViewModel {
        return SignalViewModelProvider.of(activity)
    }

    protected fun getSigFunInvokerName(property: KProperty<*>): String {
        return property.name
    }

    private fun castToSigSlotId(newValue: Any): Boolean {
        if (newValue is SigSlotId) {
            sigSlotId = newValue
            return true
        }

        return false
    }
}