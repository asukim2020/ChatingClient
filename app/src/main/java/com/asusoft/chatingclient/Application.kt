package com.asusoft.chatingclient

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


class Application : Application() {
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
}