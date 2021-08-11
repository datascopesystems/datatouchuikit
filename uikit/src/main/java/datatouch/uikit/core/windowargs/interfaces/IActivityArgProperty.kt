package datatouch.uikit.core.windowargs.interfaces

import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KProperty

interface IActivityArgProperty<V> {
    operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): V
    operator fun setValue(thisRef: AppCompatActivity, property: KProperty<*>, value: V)
}