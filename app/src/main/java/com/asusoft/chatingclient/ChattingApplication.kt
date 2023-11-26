package com.asusoft.chatingclient

import android.app.Application
import android.content.res.Resources
import android.widget.Toast
import androidx.annotation.StringRes
import com.asusoft.chatingclient.util.TAG
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class ChattingApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: ChattingApplication

        fun showToast(@StringRes stringId: Int, vararg args: Any?) {
            instance.showToast(stringId, args)
        }

        fun showTestToast(message: String) {
            instance.showTestToast(message)
        }
    }

    private var toast: Toast? = null

    override fun onCreate() {
        super.onCreate()
        settingLogger()
    }

    private fun settingLogger() {
        Logger.addLogAdapter(AndroidLogAdapter())

        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("ASU")
            .build()

        Logger.addLogAdapter(DiskLogAdapter(formatStrategy))
    }

    fun showToast(@StringRes stringId: Int, vararg args: Any?) {
        val message: String = try {
            getString(stringId).format(*args) // * 을 붙이지 않으면 args.toString 값이 표시가 된다.
        } catch (e: Resources.NotFoundException) {
            e.localizedMessage?.let { Logger.t(TAG.TOAST).e(it) }
            return
        }

        if (message.isEmpty()) {
            Logger.t(TAG.TOAST).e("don\'t show toast because ")
        } else {
            val longMessageSize = 73 // 해상도 font size 고려
            if (longMessageSize < message.length) {
                Logger.t(TAG.TOAST).w("toast message is too long")
            }
            CoroutineScope(Dispatchers.Main).launch {
                if (toast != null) {
                    toast?.cancel()
                }
                toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT);
                toast?.show()
            }

        }
    }

    fun showTestToast(message: String) {
        if (message.isEmpty()) return

        if (BuildConfig.DEBUG) {
            val longMessageSize = 73 // 해상도 font size 고려
            if (longMessageSize < message.length) {
                Logger.w("toast message is too long")
            }

            CoroutineScope(Dispatchers.Main).launch {
                if (toast != null) {
                    toast?.cancel()
                }
                toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT);
                toast?.show()
            }
        } else {
            Logger.t(TAG.TOAST).d(message)
        }
    }
}