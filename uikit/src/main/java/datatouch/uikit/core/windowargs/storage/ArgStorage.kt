package datatouch.uikit.core.windowargs.storage

import kotlin.reflect.cast

internal abstract class ArgStorage<V>(
    protected var isNullable: Boolean) {

    protected var internalValue: V? = null

    protected var isInitialized = false

    protected abstract fun castToType(value: Any): V?

    protected fun castToInternalValueType(newValue: Any): Boolean {
        val localValue = internalValue ?: return false

        val castedValue = runCatching {
            localValue::class.cast(newValue)
        }.getOrNull()

        when (castedValue != null) {
            true -> assignInternalValue(castedValue)
            else -> useDefaultInternalValue()
        }

        // If cast unsuccessful then we got different types
        // and return true to stop farther processing

        return true
    }

    protected fun checkNullableAccess(value: Any?): Boolean {
        return when (value == null) {
            true -> isNullable
            else -> true
        }
    }

    protected fun assignInternalValue(value: V?) {
        if (checkNullableAccess(value)) {
            internalValue = value
        }
        isInitialized = true
    }

    protected fun useDefaultInternalValue() {
        isInitialized = true
    }
}