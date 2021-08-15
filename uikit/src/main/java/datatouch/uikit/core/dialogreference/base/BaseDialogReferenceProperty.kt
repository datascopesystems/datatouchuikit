package datatouch.uikit.core.dialogreference.base

import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import datatouch.uikit.core.dialogreference.interfaces.IDialogInstanceProvider
import datatouch.uikit.core.windowargs.argsmap.ArgsMap
import java.util.*

internal abstract class BaseDialogReferenceProperty<T : DialogFragment>(
    private var instanceCreator: (() -> T)?
) : IDialogInstanceProvider<T> {

    private var isBusy = false
    protected var tag: String? = null
    protected var fragment: T? = null

    override fun getInstance(fragmentManager: FragmentManager?): T? {
        if (fragment == null) {
            fragment = findByTag(fragmentManager)
        }
        return fragment
    }

    override fun getOrCreateInstance(fragmentManager: FragmentManager?): T? {
        if (getInstance(fragmentManager) == null) {
            fragment = instanceCreator?.invoke()
        }
        instanceCreator = null
        return fragment
    }

    override fun provideTag(): String? {
        return tag
    }

    override fun isBusy(): Boolean {
        return isBusy
    }

    override fun isNotBusy(): Boolean = !isBusy()

    override fun setBusyFlag(v: View?) {
        isBusy = true
        when (v == null) {
            true -> isBusy = false
            else -> v.post { isBusy = false }
        }
    }

    protected fun restoreOrCreateTag(propertyName: String, argsMap: ArgsMap): String {
        val restoredTag = argsMap.get(propertyName)
        if (restoredTag is String) {
            return restoredTag
        }

        val uuid = UUID.randomUUID().toString()
        val newTag = "$propertyName:$uuid"
        argsMap.set(propertyName, newTag)

        return newTag
    }

    @Suppress("UNCHECKED_CAST")
    private fun findByTag(fragmentManager: FragmentManager?): T? {
        val f = fragmentManager?.findFragmentByTag(tag) ?: return null
        return f as T
    }
}