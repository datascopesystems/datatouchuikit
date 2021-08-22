package datatouch.uikit.core.fragmentsignaling.consumer

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import datatouch.uikit.core.fragmentsignaling.interfaces.ISigFactoryOptions
import kotlinx.coroutines.CoroutineScope

internal class FragmentSignalConsumer(opt: ISigFactoryOptions) : SignalConsumer(opt) {

    private var fragment: Fragment? = null

    fun configure(fragment: Fragment) {
        if (this.fragment == null) {
            this.fragment = fragment
            startObserver(fragment)
        }
    }

    override fun onConsumerInit() {
        fragment?.let {
            startFlowConsumer(it, getSharedViewModel(it))
        }
    }

    override fun onConsumerDestroy() {
        fragment = null
    }

    override fun getCoroutineScope(): CoroutineScope? {
        return fragment?.lifecycleScope
    }
}