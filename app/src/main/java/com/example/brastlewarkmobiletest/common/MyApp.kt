package com.example.brastlewarkmobiletest.common

import android.app.Application

class MyApp : Application() {

        init {
            instance = this
        }

        companion object {
            private var instance: MyApp? = null

            fun applicationContext() : MyApp {
                return instance as MyApp
            }
        }

        override fun onCreate() {
            super.onCreate()
        }
}