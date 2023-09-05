package com.uos.cokcok.initializer

import android.content.Context
import androidx.startup.Initializer
import com.uos.cokcok.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

class LoggerInitializer: Initializer<Unit> {
    override fun create(context: Context) {

        // Logger stack trace를 5개로 조절 및 디버그 시에만 로그 표시.
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(5)
            .build()
        Logger.addLogAdapter(object: AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}