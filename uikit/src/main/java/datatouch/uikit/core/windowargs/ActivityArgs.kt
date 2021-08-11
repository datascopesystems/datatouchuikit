package datatouch.uikit.core.windowargs

import android.content.Intent
import datatouch.uikit.core.windowargs.base.makeIntentKeyName
import datatouch.uikit.core.windowargs.converter.ArgsConverter
import datatouch.uikit.core.windowargs.interfaces.IActivityArgProperty
import datatouch.uikit.core.windowargs.property.ListArgProperty
import datatouch.uikit.core.windowargs.property.ListArgPropertyNullable
import datatouch.uikit.core.windowargs.property.SerializableArgProperty
import datatouch.uikit.core.windowargs.property.SerializableArgPropertyNullable
import java.io.Serializable
import kotlin.reflect.KMutableProperty

object ActivityArgs {

    // If we add new function for new argument type
    // then we need to add corresponding putArg(...) Intent extension
    // below in this file

    fun <T : Serializable> of(arg: T): IActivityArgProperty<T> =
        SerializableArgProperty<T>().setInitialValue(arg)

    fun <T : Serializable> ofNullable(arg: T? = null): IActivityArgProperty<T?> =
        SerializableArgPropertyNullable<T>().setInitialValue(arg)

    fun <T : Serializable> ofList(arg: List<T> = listOf()): IActivityArgProperty<List<T>> =
        ListArgProperty<T>().setInitialValue(arg)

    fun <T : Serializable> ofListNullable(arg: List<T>? = null): IActivityArgProperty<List<T>?> =
        ListArgPropertyNullable<T>().setInitialValue(arg)
}


// Activity Arguments Intent Extensions

fun <T : Serializable?> Intent.putArg(property: KMutableProperty<T>, value: T): Intent {
    putExtra(makeIntentKeyName(property.name), value)
    return this
}

fun <T : Serializable, V : List<T>?> Intent.putArg(property: KMutableProperty<V>, value: V): Intent {
    val newValue = ArgsConverter.listToArrayListNullable(value)
    putExtra(makeIntentKeyName(property.name), newValue)
    return this
}