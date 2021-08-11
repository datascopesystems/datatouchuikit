package datatouch.uikit.core.extensions

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

fun <T : AppCompatActivity> Intent.withClass(context: Context?, klass: KClass<T>): Intent {
    if (context != null) {
        setClass(context, klass.java)
    }
    return this
}

fun Intent.startActivity(context: Context?) {
    context?.startActivity(this)
}