package com.example.islamy_project

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyApplication : Application() {
    companion object {

        val RADIO_PLAYER_NOTIFICATION_CHANNEL = "radio_channel"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = getString(R.string.radio_player_channel)
            val descriptionsText = getString(R.string.radio_player_channel_description)
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel =
                NotificationChannel(RADIO_PLAYER_NOTIFICATION_CHANNEL, name, importance).apply {
                    description = descriptionsText
                }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;
            notificationManager.createNotificationChannel(channel)
        }
    }
}