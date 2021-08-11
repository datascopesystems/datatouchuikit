package datatouch.uikit.core.windowargs.storage

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import datatouch.uikit.core.windowargs.argsmap.ActivityArgsMap
import datatouch.uikit.core.windowargs.argsmap.ArgsMap
import datatouch.uikit.core.windowargs.argsmap.FragmentArgsMap
import kotlin.reflect.KProperty
import kotlin.reflect.KType
import kotlin.reflect.full.createType

internal abstract class ArgPropertyStorage<V>(isNullable: Boolean)
    : ArgStorage<V>(isNullable) {

    protected fun getArg(f: Fragment, property: KProperty<*>): V? {
        return when (isInitialized || ArgsMap.isEmptyArguments(f)) {
            true -> internalValue
            else -> getArg(FragmentArgsMap(f), property)
        }
    }

    protected fun getArg(a: AppCompatActivity, property: KProperty<*>): V? {
        return when (isInitialized || ArgsMap.isEmptyArguments(a)) {
            true -> internalValue
            else -> getArg(ActivityArgsMap(a), property)
        }
    }

    protected fun setArg(f: Fragment, property: KProperty<*>, value: V?) {
        assignInternalValue(value)
        setArg(FragmentArgsMap(f), property, value)
    }

    protected fun setArg(f: AppCompatActivity, property: KProperty<*>, value: V?) {
        assignInternalValue(value)
        setArg(ActivityArgsMap(f), property, value)
    }

    private fun getArg(map: ArgsMap, property: KProperty<*>): V? {
        if (map.isNotExist(property.name)) {
            return internalValue
        }

        restoreInternalValue(map, property)

        return internalValue
    }

    private fun setArg(map: ArgsMap, property: KProperty<*>, value: V?) {
        if (saveNullArgumentIfRequired(map, property, value)) {
            return
        }
        map.set(property.name, value)
    }

    private fun saveNullArgumentIfRequired(map: ArgsMap, property: KProperty<*>, value: V?): Boolean {
        if (value == null) {
            if (property.returnType.isMarkedNullable) {
                map.set(property.name, null)
            }
            return true
        }
        return false
    }

    private fun restoreInternalValue(map: ArgsMap, property: KProperty<*>) {
        val value = map.get(property.name)
        if (!checkNullableAccess(value)) {
            return
        }

        if (value == null) {
            assignInternalValue(null)
            return
        }

        if (castToInternalValueType(value)) {
            return
        }

        if (castToPropertyType(value, property)) {
            return
        }
    }

    private fun castToPropertyType(newValue: Any, property: KProperty<*>): Boolean {
        val dstType = property.returnType
        if (isNotGenericType(dstType)) {
            val srcType = makeTypeFromValue(newValue, isNullable)
            if (srcType != dstType) {
                useDefaultInternalValue()
                return false
            }
        }

        val castedValue = castToType(newValue)
        if (castedValue != null) {
            assignInternalValue(castedValue)
            return true
        }

        useDefaultInternalValue()
        return false
    }

    private fun isNotGenericType(type: KType): Boolean {
        return type.arguments.isEmpty()
    }

    private fun makeTypeFromValue(value: Any, isValueTypeNullable: Boolean): KType? {
        return runCatching {
            value::class.createType(nullable = isValueTypeNullable)
        }.getOrNull()
    }
}