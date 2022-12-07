package com.example.pillee.jetpackcompnavigation.ui

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.alarms.AlarmCreator

class NotifActivity: ComponentActivity() {
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Notifications
        notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelID = "com.ebookfrenzy.notifydemo.news"

        val notification = NotificationCompat.Builder(this@NotifActivity, channelID)
            .setSmallIcon(R.drawable.pillelogo)
            .setContentTitle("Pille")
            .setContentText("Notification for pills")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()



        notificationManager?.notify(23, notification)

    }

}