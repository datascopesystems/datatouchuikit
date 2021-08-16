package datatouch.uikit.core.windowargs

import android.os.Bundle
import androidx.fragment.app.Fragment
import datatouch.uikit.core.windowargs.argsmap.ArgsMap
import datatouch.uikit.core.windowargs.interfaces.IFragmentArgProperty
import datatouch.uikit.core.windowargs.converter.ArgsConverter
import datatouch.uikit.core.windowargs.interfaces.IFragmentArguments
import datatouch.uikit.core.windowargs.property.ListArgProperty
import datatouch.uikit.core.windowargs.property.ListArgPropertyNullable
import datatouch.uikit.core.windowargs.property.SerializableArgProperty
import datatouch.uikit.core.windowargs.property.SerializableArgPropertyNullable
import java.io.Serializable
import kotlin.reflect.KMutableProperty


/**
 * Extension property which initialize fragment arguments Bundle
 */
val Fragment.FragmentArgs: IFragmentArguments get() {
    FragmentArguments.initOnce(this)
    return FragmentArguments
}

internal object FragmentArguments : IFragmentArguments {

    // If we add new function for new argument type
    // then we need to add corresponding putArg(...) Bundle extension
    // below in this file

    override fun <T : Serializable> of(arg: T): IFragmentArgProperty<T> =
        SerializableArgProperty<T>().setInitialValue(arg)

    override fun <T : Serializable> ofNullable(arg: T?): IFragmentArgProperty<T?> =
        SerializableArgPropertyNullable<T>().setInitialValue(arg)

    override fun <T : Serializable> ofList(arg: List<T>): IFragmentArgProperty<List<T>> =
        ListArgProperty<T>().setInitialValue(arg)

    override fun <T : Serializable> ofListNullable(arg: List<T>?): IFragmentArgProperty<List<T>?> =
        ListArgPropertyNullable<T>().setInitialValue(arg)

    /**
     * Creates Bundle() instance and assign it to fragment using f.setArguments(...)
     * only if fragment has no args bundle yet
     *
     * If we want use FragmentArgs just to store some data, and fragment has no any arguments,
     * then we need to call f.setArguments(...) before fragment added to FragmentManager,
     * initOnce(...) will do that.
     */
    internal fun initOnce(f: Fragment) = ArgsMap.getOrCreateArgsBundle(f)
}

// Fragment Arguments Bundle Extensions

fun <T : Serializable?> Bundle.putArg(property: KMutableProperty<T>, value: T): Bundle {
    putSerializable(property.name, value)
    return this
}

fun <T : Serializable, V : List<T>?> Bundle.putArg(property: KMutableProperty<V>, value: V): Bundle {
    val newValue = ArgsConverter.listToArrayListNullable(value)
    putSerializable(property.name, newValue)
    return this
}
