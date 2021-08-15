package datatouch.uikit.core.dialogreference

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import datatouch.uikit.core.dialogreference.interfaces.IActivityDialogRefProperty
import datatouch.uikit.core.dialogreference.interfaces.IFragmentDialogRefProperty
import datatouch.uikit.core.dialogreference.property.ActivityDialogRefProperty
import datatouch.uikit.core.dialogreference.property.FragmentDialogRefProperty

object DialogRef {

    /**
     * Create reference to DialogFragment inside parent Fragment
     *
     * @param parentFragment - fragment which holds returned property
     * @param instanceCreator - lambda returns DialogFragment instance
     * @return property which holds DialogFragment instance
     */
    fun <T : DialogFragment> make(parentFragment: Fragment, instanceCreator: () -> T)
            : IFragmentDialogRefProperty<T> {
        return FragmentDialogRefProperty(parentFragment, instanceCreator)
    }

    /**
     * Create reference to DialogFragment inside parent Activity
     *
     * @param parentActivity - activity which holds returned property
     * @param instanceCreator - lambda returns DialogFragment instance
     * @return property which holds DialogFragment instance
     */
    fun <T : DialogFragment> make(parentActivity: AppCompatActivity, instanceCreator: () -> T)
    : IActivityDialogRefProperty<T> {
        return ActivityDialogRefProperty(parentActivity, instanceCreator)
    }
}