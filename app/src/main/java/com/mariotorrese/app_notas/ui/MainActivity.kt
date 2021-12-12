package com.mariotorrese.app_notas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.mariotorrese.app_notas.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseMessaging.getInstance().subscribeToTopic("Administrador")
        getNotificationId()
    }

    private fun getNotificationId() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener{ task ->
            val token = task.result
            Log.d("Notas:", token!!)
        }
    }
}