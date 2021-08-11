package datatouch.uikit.core.windowargs.property

import datatouch.uikit.core.windowargs.base.BaseArgProperty
import java.io.Serializable

internal class SerializableArgPropertyNullable<V : Serializable>
    : BaseArgProperty<V?, V?>(true) {

    override fun convertPropertyToValue(src: V?): V? = src

    override fun convertValueToProperty(src: V?): V? = src

    @Suppress("UNCHECKED_CAST")
    override fun castToType(value: Any): V? {
        return when (isNullable) {
            true -> runCatching { value as V? }.getOrNull()
            else -> runCatching { value as V }.getOrNull()
        }
    }
}