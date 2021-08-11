package datatouch.uikit.core.windowargs.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import datatouch.uikit.core.windowargs.interfaces.IActivityArgProperty
import datatouch.uikit.core.windowargs.interfaces.IFragmentArgProperty
import datatouch.uikit.core.windowargs.storage.ArgPropertyStorage
import kotlin.reflect.KProperty

internal abstract class BaseArgProperty<TProp, TVal>(isNullable: Boolean)
    : ArgPropertyStorage<TVal>(isNullable),
    IFragmentArgProperty<TProp>,
    IActivityArgProperty<TProp> {

    fun setInitialValue(initialValue: TProp) = apply {
        internalValue = convertPropertyToValue(initialValue)
    }

    protected abstract fun convertPropertyToValue(src: TProp): TVal
    protected abstract fun convertValueToProperty(src: TVal): TProp

    // Fragment property implementation

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): TProp {
        val argValue = getArg(thisRef, property)
        return convertValueToProperty(argValue as TVal)
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: TProp) {
        val argValue = convertPropertyToValue(value)
        setArg(thisRef, property, argValue)
    }

    // Activity property implementation

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): TProp {
        val argValue = getArg(thisRef, property)
        return convertValueToProperty(argValue as TVal)
    }

    override fun setValue(thisRef: AppCompatActivity, property: KProperty<*>, value: TProp) {
        val argValue = convertPropertyToValue(value)
        setArg(thisRef, property, argValue)
    }
}