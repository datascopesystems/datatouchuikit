package datatouch.uikit.core.fragmentsignaling.consumer

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope

internal class FragmentSignalConsumer : SignalConsumer() {
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