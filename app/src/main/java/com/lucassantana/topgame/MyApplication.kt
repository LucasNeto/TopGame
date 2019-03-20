package com.lucassantana

import android.app.Application
import android.content.Context

/**
 * Created by capgemini on 18/03/2019.
 */


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        appContext = getApplicationContext()
    }

    companion object {
        var instance: MyApplication? = null
            private set
        var appContext: Context? = null
            set(mAppContext) {
                field = mAppContext
            }
    }
}
