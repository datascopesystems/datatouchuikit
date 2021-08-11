package datatouch.uikit.core.windowargs.argsmap

import android.os.Bundle
import androidx.fragment.app.Fragment
import java.io.Serializable

internal class FragmentArgsMap(f: Fragment) : ArgsMap() {

    private val bundle: Bundle = getOrCreateArgsBundle(f)

    override fun set(key: String, value: Any?) {
        if (value == null) {
            bundle.putSerializable(key, null)
            return
        }

        if (value is Serializable) {
            bundle.putSerializable(key, value)
        }
    }

    override fun get(key: String): Any? {
        return bundle.get(key)
    }

    override fun isNotExist(key: String): Boolean {
        return !bundle.containsKey(key)
    }
}