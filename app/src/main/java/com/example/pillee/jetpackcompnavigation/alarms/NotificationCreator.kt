package com.example.pillee.jetpackcompnavigation.alarms

import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Vibrator
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.ui.MainActivity

class NotificationCreator(){
    private var notificationManager: NotificationManagerCompat? = null

    fun createNotification(context: android.content.Context, intent: Intent, title: String, description: String){
        val tapResultIntent = Intent(context, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            tapResultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notification = NotificationCompat.Builder(context, "com.ebookfrenzy.notifydemo.news")
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.pillelogo)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setSound(uri)
            .build()



        notificationManager = context?.let { NotificationManagerCompat.from(it) }
        //notification?.let { taskInfo?.let { it1 -> notificationManager?.notify(it1.id, it) } }

        //Setting a random to the id of the notification bc they need different ids
        notificationManager?.notify((0..1000).random(), notification)
    }
}