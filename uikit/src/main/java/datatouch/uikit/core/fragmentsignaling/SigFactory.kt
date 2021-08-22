package datatouch.uikit.core.fragmentsignaling

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import datatouch.uikit.core.fragmentsignaling.variation.slotcontainer.SlotCreationContainer
import datatouch.uikit.core.fragmentsignaling.consumer.property.ActivitySignalConsumerProperty
import datatouch.uikit.core.fragmentsignaling.consumer.property.FragmentSignalConsumerProperty
import datatouch.uikit.core.fragmentsignaling.interfaces.ISigFactoryOptions
import datatouch.uikit.core.fragmentsignaling.options.GlobalOptionsProvider
import datatouch.uikit.core.fragmentsignaling.variation.builders.*
import datatouch.uikit.core.windowargs.argsmap.ArgsMap
import kotlin.properties.ReadOnlyProperty


/**
 * Extension property which returns SigFactory for fragment
 * and initialize fragment arguments Bundle
 */
val Fragment.SigFactory: FragmentSigFactory get() {
    FragmentSigFactory.initOnce(this)
    return FragmentSigFactory
}

/**
 * Extension property which returns SigFactory for activity
 * and initialize activity Intent extra bundle
 */
val AppCompatActivity.SigFactory: ActivitySigFactory get() {
    ActivitySigFactory.initOnce(this)
    return ActivitySigFactory
}


/**
 * SigFactory which can be used in Fragments only
 */
object FragmentSigFactory {

    /**
     *  Create sigFun builder for signal without params
     */
    fun sigFun(): BuilderFragmentSigFun0 {
        return BuilderFragmentSigFun0()
    }

    /**
     *  Create sigFun builder for signal with 1 param
     *
     *  A - param type
     *  @param a - must be ignored; it is used for overload only
     */
    fun <A> sigFun(a: A? = null): BuilderFragmentSigFun1<A> {
        return BuilderFragmentSigFun1()
    }

    /**
     *  Create sigFun builder for signal with 2 params
     *
     *  A, B - param types
     *  @param a - must be ignored; it is used for overload only
     *  @param b - must be ignored; it is used for overload only
     */
    fun <A, B> sigFun(a: A? = null, b: B? = null): BuilderFragmentSigFun2<A, B> {
        return BuilderFragmentSigFun2()
    }

    /**
     * Create slot container for fragment
     * @return readonly property delegate for SlotCreationContainer type
     */
    fun <T : Fragment> slotContainer(): ReadOnlyProperty<T, SlotCreationContainer> {
        return FragmentSignalConsumerProperty(options().copy())
    }

    fun options(): ISigFactoryOptions = GlobalOptionsProvider.get()

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


/**
 * SigFactory which can be used in Activities only
 */
object ActivitySigFactory {

    /**
     *  Create sigFun builder for signal without params
     */
    fun sigFun(): BuilderActivitySigFun0 {
        return BuilderActivitySigFun0()
    }

    /**
     *  Create sigFun builder for signal with 1 param
     *
     *  A - param type
     *  @param a - must be ignored; it is used for overload only
     */
    fun <A> sigFun(a: A? = null): BuilderActivitySigFun1<A> {
        return BuilderActivitySigFun1()
    }

    /**
     *  Create sigFun builder for signal with 2 params
     *
     *  A, B - param types
     *  @param a - must be ignored; it is used for overload only
     *  @param b - must be ignored; it is used for overload only
     */
    fun <A, B> sigFun(a: A? = null, b: B? = null): BuilderActivitySigFun2<A, B> {
        return BuilderActivitySigFun2()
    }

    /**
     * Create slot container for activity
     * @return readonly property delegate for SlotCreationContainer type
     */
    fun <T : AppCompatActivity> slotContainer(): ReadOnlyProperty<T, SlotCreationContainer> {
       return ActivitySignalConsumerProperty(options().copy())
    }

    fun options(): ISigFactoryOptions = GlobalOptionsProvider.get()

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