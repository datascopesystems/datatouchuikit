package datatouch.uikit.core.windowargs.interfaces

import androidx.fragment.app.Fragment
import kotlin.reflect.KProperty

interface IFragmentArgProperty<V> {
    operator fun getValue(thisRef: Fragment, property: KProperty<*>): V
    operator fun setValue(thisRef: Fragment, property: KProperty<*>, value: V)
}