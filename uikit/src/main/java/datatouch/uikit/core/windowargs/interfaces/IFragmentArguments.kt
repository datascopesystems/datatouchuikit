package datatouch.uikit.core.windowargs.interfaces

import datatouch.uikit.core.windowargs.property.ListArgProperty
import datatouch.uikit.core.windowargs.property.ListArgPropertyNullable
import datatouch.uikit.core.windowargs.property.SerializableArgProperty
import datatouch.uikit.core.windowargs.property.SerializableArgPropertyNullable
import java.io.Serializable

interface IFragmentArguments {
    fun <T : Serializable> of(arg: T): IFragmentArgProperty<T> =
        SerializableArgProperty<T>().setInitialValue(arg)

    fun <T : Serializable> ofNullable(arg: T? = null): IFragmentArgProperty<T?> =
        SerializableArgPropertyNullable<T>().setInitialValue(arg)

    fun <T : Serializable> ofList(arg: List<T> = listOf()): IFragmentArgProperty<List<T>> =
        ListArgProperty<T>().setInitialValue(arg)

    fun <T : Serializable> ofListNullable(arg: List<T>? = null): IFragmentArgProperty<List<T>?> =
        ListArgPropertyNullable<T>().setInitialValue(arg)
}