package datatouch.uikit.core.fragmentsignaling.consumer

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope

internal class ActivitySignalConsumer : SignalConsumer() {
    private var activity: AppCompatActivity? = null

    fun configure(activity: AppCompatActivity) {
        if (this.activity == null) {
            this.activity = activity
            startObserver(activity)
        }
    }

    override fun onConsumerInit() {
        activity?.let {
            startFlowConsumer(it, getSharedViewModel(it))
        }
    }

    override fun onConsumerDestroy() {
        activity = null
    }

    override fun getCoroutineScope(): CoroutineScope? {
        return activity?.lifecycleScope
    }
}