package datatouch.uikit.components.logger

import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

object Logger {

    private var isLoggingDisabled = false

    @JvmStatic
    fun e(ex: Exception) {
        if (isLoggingDisabled) return
        Logger.getGlobal().log(Level.SEVERE, ex.message, ex)
    }

    @JvmStatic
    fun e(ex: Throwable) {
        if (isLoggingDisabled) return
        Logger.getGlobal().log(Level.SEVERE, ex.message, ex)
    }

    @JvmStatic
    fun i(message: String?) {
        if (isLoggingDisabled) return
        Logger.getGlobal().log(Level.INFO, message)
    }

    @JvmStatic
    fun e(message: String?) {
        if (isLoggingDisabled) return
        Logger.getGlobal().log(Level.SEVERE, message)
    }

    @JvmStatic
    fun e(tag: String, message: String) {
        if (isLoggingDisabled) return
        Logger.getGlobal().log(Level.SEVERE, "$tag --> $message")
    }

    @JvmStatic
    fun i(message: Int) {
        if (isLoggingDisabled) return
        Logger.getGlobal().log(Level.INFO, message.toString())
    }

    @JvmStatic
    fun printStackTraceLog() =
        Logger.getGlobal().log(Level.SEVERE, Arrays.toString(Throwable().stackTrace))

    @JvmStatic
    fun printStackTrace() =
        Logger.getGlobal().log(Level.SEVERE, Arrays.toString(Throwable().stackTrace))

}