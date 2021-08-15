package datatouch.uikit.core.dialogreference.reference

import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import datatouch.uikit.core.dialogreference.base.BaseDialogReference
import datatouch.uikit.core.dialogreference.interfaces.IDialogInstanceProvider

internal class FragmentDialogRef<T : DialogFragment>(
    private var parentFragment: Fragment?,
    instanceProvider: IDialogInstanceProvider<T>?
) : BaseDialogReference<T>(parentFragment?.childFragmentManager, instanceProvider) {

    override fun getParentRootView(): View? {
        return parentFragment?.view
    }

    override fun drop() {
        super.drop()
        parentFragment = null
    }
}