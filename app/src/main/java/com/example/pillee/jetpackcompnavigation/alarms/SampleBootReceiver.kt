package com.example.pillee.jetpackcompnavigation.alarms

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.*
import android.app.TaskInfo
import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.ui.MainActivity
import com.example.pillee.jetpackcompnavigation.ui.NotifActivity
import okhttp3.internal.notify


class SampleBootReceiver : BroadcastReceiver() {
    private var notificationManager: NotificationManagerCompat? = null


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onReceive(context: Context, intent: Intent) {
        /*
        val vibrator = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(4000)
        Toast.makeText(context, "Alarm sounding",Toast.LENGTH_SHORT).show()
        Log.d("alarm","received")
        var alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        if (alarmUri == null){
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }

        var ringtone = RingtoneManager.getRingtone(context,alarmUri)
        ringtone.play()
         */
        Log.d("TAG", "La alarma se esta haciendoooooooo")
        val taskInfo = intent?.getSerializableExtra("task_info") as? TaskInfo
        val tapResultIntent = Intent(context, MainActivity::class.java)
        val pendingIntent: PendingIntent = getActivity( context,0,tapResultIntent,FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)
            val notification = NotificationCompat.Builder(context, "com.ebookfrenzy.notifydemo.news")
                .setContentTitle("Task Reminder")
                .setContentText("Notificacion jejeje")
                .setSmallIcon(R.drawable.pills_1)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()

        notificationManager = context?.let { NotificationManagerCompat.from(it) }
        //notification?.let { taskInfo?.let { it1 -> notificationManager?.notify(it1.id, it) } }
        notificationManager?.notify(23, notification)

    }





}
