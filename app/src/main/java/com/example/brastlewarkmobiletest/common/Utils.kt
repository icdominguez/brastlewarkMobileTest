package com.example.brastlewarkmobiletest.common

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import java.net.URL

class Utils {

    companion object Constants {
        var BASE_URL = "https://raw.githubusercontent.com/rrafols/mobile_test/master/"
    }

    fun generateGlideUrl(url: String) : GlideUrl {
        val USER_AGENT = "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.181 Mobile Safari/537.36"
        val glideUrl = GlideUrl(
            URL(url),
            LazyHeaders.Builder().addHeader("User-Agent", USER_AGENT).build())

        return glideUrl

    }
}