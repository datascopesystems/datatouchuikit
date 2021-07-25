package datatouch.uikit.core.utils.clicklisteners

import android.view.View


fun View.setOnClickTimeoutAfter(timeout: Long, action: () -> Unit) {
    this.setOnClickListener(TimeoutAfterClickListener(timeout, action))
}

private class TimeoutAfterClickListener(private var timeout: Long,
                                        private val action : () -> Unit) : View.OnClickListener {

    private var isEnabled = true

    override fun onClick(v: View?) {
        if (v == null || timeout <= 0) {
            action.invoke()
            return
        }

        if (isEnabled) {
            disable()
            action.invoke()
            enableAfterTimeout(v)
        }
    }

    private fun disable() {
        isEnabled = false
    }

    private fun enableAfterTimeout(v: View?) {
        when (v == null) {
            true -> isEnabled = true
            else -> v.postDelayed({ isEnabled = true }, timeout)
        }
    }
}