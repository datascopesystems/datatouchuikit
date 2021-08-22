package datatouch.uikit.core.fragmentsignaling.interfaces

import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KProperty

interface ISigFunActivityProperty<V> {
    operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): V
    operator fun setValue(thisRef: AppCompatActivity, property: KProperty<*>, value: V)
}