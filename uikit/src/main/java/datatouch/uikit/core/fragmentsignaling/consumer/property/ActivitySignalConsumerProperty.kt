package datatouch.uikit.core.fragmentsignaling.consumer.property

import androidx.appcompat.app.AppCompatActivity
import datatouch.uikit.core.fragmentsignaling.consumer.ActivitySignalConsumer
import datatouch.uikit.core.fragmentsignaling.interfaces.ISigFactoryOptions
import datatouch.uikit.core.fragmentsignaling.variation.slotcontainer.SlotCreationContainer
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


internal class ActivitySignalConsumerProperty<T : AppCompatActivity>(
    private val opt: ISigFactoryOptions
) : ReadOnlyProperty<T, SlotCreationContainer> {

    private var signalConsumer: ActivitySignalConsumer? = null

    override fun getValue(thisRef: T, property: KProperty<*>): SlotCreationContainer {
        if (signalConsumer == null) {
            signalConsumer = ActivitySignalConsumer(opt)
            signalConsumer!!.configure(thisRef)
        }
        return signalConsumer!!
    }
}