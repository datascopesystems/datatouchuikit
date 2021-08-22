package datatouch.uikit.core.fragmentsignaling.interfaces

import androidx.fragment.app.Fragment
import kotlin.reflect.KProperty

interface ISigFunFragmentProperty<V> {
    operator fun getValue(thisRef: Fragment, property: KProperty<*>): V
    operator fun setValue(thisRef: Fragment, property: KProperty<*>, value: V)
}