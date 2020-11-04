package com.example.uidesign.home

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebase : FirebaseInstanceIdService() {
    private val TAG = "MyFirebase"

    override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "onTokenRefresh: $token")
    }
}