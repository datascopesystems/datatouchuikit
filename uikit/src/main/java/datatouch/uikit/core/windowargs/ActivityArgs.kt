package datatouch.uikit.core.windowargs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import datatouch.uikit.core.windowargs.argsmap.ArgsMap
import datatouch.uikit.core.windowargs.base.makeIntentKeyName
import datatouch.uikit.core.windowargs.converter.ArgsConverter
import datatouch.uikit.core.windowargs.interfaces.IActivityArgProperty
import datatouch.uikit.core.windowargs.interfaces.IActivityArguments
import datatouch.uikit.core.windowargs.property.ListArgProperty
import datatouch.uikit.core.windowargs.property.ListArgPropertyNullable
import datatouch.uikit.core.windowargs.property.SerializableArgProperty
import datatouch.uikit.core.windowargs.property.SerializableArgPropertyNullable
import java.io.Serializable
import kotlin.reflect.KMutableProperty

/**
 * Extension property which initialize activity Intent extra bundle
 */
val AppCompatActivity.ActivityArgs: IActivityArguments get() {
    ActivityArguments.initOnce(this)
    return ActivityArguments
}

internal object ActivityArguments : IActivityArguments {

    // If we add new function for new argument type
    // then we need to add corresponding putArg(...) Intent extension
    // below in this file

    override fun <T : Serializable> of(arg: T): IActivityArgProperty<T> =
        SerializableArgProperty<T>().setInitialValue(arg)

    override fun <T : Serializable> ofNullable(arg: T?): IActivityArgProperty<T?> =
        SerializableArgPropertyNullable<T>().setInitialValue(arg)

    override fun <T : Serializable> ofList(arg: List<T>): IActivityArgProperty<List<T>> =
        ListArgProperty<T>().setInitialValue(arg)

    override fun <T : Serializable> ofListNullable(arg: List<T>?): IActivityArgProperty<List<T>?> =
        ListArgPropertyNullable<T>().setInitialValue(arg)

    /**
     * Creates activity Intent instance and assign it to activity using a.setIntent(...)
     * only if activity has no intent yet
     *
     * If we want use ActivityArgs just to store some data, and activity has no any arguments,
     * then we need to call a.setIntent(...) before activity started,
     * initOnce(...) will do that.
     */
    internal fun initOnce(a: AppCompatActivity) = ArgsMap.getOrCreateArgsIntent(a)
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