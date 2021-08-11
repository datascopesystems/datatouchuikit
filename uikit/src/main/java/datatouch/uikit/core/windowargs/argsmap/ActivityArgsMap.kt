package datatouch.uikit.core.windowargs.argsmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import datatouch.uikit.core.windowargs.base.makeIntentKeyName
import java.io.Serializable

internal class ActivityArgsMap(a: AppCompatActivity) : ArgsMap() {

    private val intent: Intent = getOrCreateArgsIntent(a)

    override fun set(key: String, value: Any?) {
        if (value == null) {
            val nullValue: Serializable? = null
            intent.putExtra(makeIntentKeyName(key), nullValue)
            return
        }

        if (value is Serializable) {
            intent.putExtra(makeIntentKeyName(key), value)
        }
    }

    override fun get(key: String): Any? {
        return intent.getSerializableExtra(makeIntentKeyName(key))
    }

    override fun isNotExist(key: String): Boolean {
        return !intent.hasExtra(makeIntentKeyName(key))
    }
}