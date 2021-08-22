package datatouch.uikit.core.fragmentsignaling.consumer.property

import androidx.fragment.app.Fragment
import datatouch.uikit.core.fragmentsignaling.consumer.FragmentSignalConsumer
import datatouch.uikit.core.fragmentsignaling.interfaces.ISigFactoryOptions
import datatouch.uikit.core.fragmentsignaling.variation.slotcontainer.SlotCreationContainer
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class FragmentSignalConsumerProperty<T : Fragment>(
    private val opt: ISigFactoryOptions
) : ReadOnlyProperty<T, SlotCreationContainer> {

    private var signalConsumer: FragmentSignalConsumer? = null

    override fun getValue(thisRef: T, property: KProperty<*>): SlotCreationContainer {
        if (signalConsumer == null) {
            signalConsumer = FragmentSignalConsumer(opt)
            signalConsumer!!.configure(thisRef)
        }
        return signalConsumer!!
    }
}