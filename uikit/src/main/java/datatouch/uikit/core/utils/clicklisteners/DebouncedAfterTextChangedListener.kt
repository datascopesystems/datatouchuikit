package datatouch.uikit.core.utils.clicklisteners

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText


fun EditText.doAfterTextChangedDebounced(timeout: Long, action: () -> Unit) {
    addTextChangedListener(DebouncedAfterTextChangedListener(this, timeout, action))
}

private class DebouncedAfterTextChangedListener(private val view: View,
                                                private var timeout: Long,
                                                private val action : () -> Unit) : TextWatcher {

    private var timePoint = 0L

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(editable: Editable?) {
        if (editable == null || timeout == 0L) {
            execAction()
            return
        }

        if (timePoint == 0L) {
            view.postDelayed(::onTimeoutElapsed, timeout)
        }

        timePoint = System.currentTimeMillis()
    }

    private fun onTimeoutElapsed() {
        val timeDelta = System.currentTimeMillis() - timePoint
        if (timeDelta >= timeout) {
            execAction()
            return
        }

        val newTimeout = timeout - timeDelta
        if (newTimeout <= 10) {
            execAction()
            return
        }

        view.postDelayed(::onTimeoutElapsed, newTimeout)
    }

    private fun execAction() {
        action.invoke()
        timePoint = 0
    }
}