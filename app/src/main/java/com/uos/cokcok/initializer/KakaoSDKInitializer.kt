package com.uos.cokcok.initializer

import android.content.Context
import androidx.startup.Initializer
import com.uos.cokcok.BuildConfig.KAKAO_NATIVE_APP_KEY
import com.kakao.sdk.common.KakaoSdk

class KakaoSDKInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        KakaoSdk.init(context, KAKAO_NATIVE_APP_KEY)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}