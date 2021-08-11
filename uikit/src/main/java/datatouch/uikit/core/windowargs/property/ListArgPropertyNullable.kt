package datatouch.uikit.core.windowargs.property

import datatouch.uikit.core.windowargs.base.BaseArgProperty
import datatouch.uikit.core.windowargs.converter.ArgsConverter
import java.io.Serializable

internal class ListArgPropertyNullable<V : Serializable>
    : BaseArgProperty<List<V>?, ArrayList<V>?>(true) {

    override fun convertPropertyToValue(src: List<V>?): ArrayList<V>? {
        return ArgsConverter.listToArrayListNullable(src)
    }

    override fun convertValueToProperty(src: ArrayList<V>?): List<V>? {
        return src
    }

    @Suppress("UNCHECKED_CAST")
    override fun castToType(value: Any): ArrayList<V>? {
        return when (isNullable) {
            true -> runCatching { value as ArrayList<V>? }.getOrNull()
            else -> runCatching { value as ArrayList<V> }.getOrNull()
        }
    }
}