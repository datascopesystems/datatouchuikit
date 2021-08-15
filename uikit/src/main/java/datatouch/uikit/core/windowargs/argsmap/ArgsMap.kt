package datatouch.uikit.core.windowargs.argsmap


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import datatouch.uikit.core.extensions.GenericExtensions.default

internal abstract class ArgsMap {
    abstract fun set(key: String, value: Any?)
    abstract fun get(key: String): Any?
    abstract fun isNotExist(key: String): Boolean

    companion object {
        fun isEmptyArguments(f: Fragment): Boolean {
            return f.arguments?.isEmpty.default(true)
        }

        fun isEmptyArguments(a: AppCompatActivity): Boolean {
            // We cannot use a.intent.getExtras().isEmpty() because
            // getExtras() creates new instance every time if extras not null
            // so return false for compatibility
            return false
        }

        fun getOrCreateArgsBundle(f: Fragment): Bundle {
            return when (val args = f.arguments) {
                null -> Bundle().also { f.arguments = it }
                else -> args
            }
        }

        fun getOrCreateArgsIntent(a: AppCompatActivity): Intent {
            return when (val args = a.intent) {
                null -> Intent().also { a.intent = it }
                else -> args
            }
        }
    }
}