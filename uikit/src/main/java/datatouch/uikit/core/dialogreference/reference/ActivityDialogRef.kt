package datatouch.uikit.core.dialogreference.reference

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import datatouch.uikit.core.dialogreference.base.BaseDialogReference
import datatouch.uikit.core.dialogreference.interfaces.IDialogInstanceProvider

internal class ActivityDialogRef<T : DialogFragment>(
    private var parentActivity: AppCompatActivity?,
    instanceProvider: IDialogInstanceProvider<T>?
) : BaseDialogReference<T>(parentActivity?.supportFragmentManager, instanceProvider) {

    override fun getParentRootView(): View? {
        var view = parentActivity?.findViewById<View>(android.R.id.content)
        if (view == null) {
            view = parentActivity?.window?.decorView?.rootView
        }
        return view
    }

    override fun drop() {
        super.drop()
        parentActivity = null
    }
}