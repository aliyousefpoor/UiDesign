package com.example.uidesign

import android.app.Application
//import com.onesignal.OneSignal

 class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()

//        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
//        OneSignal.startInit(this)
//            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//            .unsubscribeWhenNotificationsAreDisabled(true)
//            .init()
    }

}