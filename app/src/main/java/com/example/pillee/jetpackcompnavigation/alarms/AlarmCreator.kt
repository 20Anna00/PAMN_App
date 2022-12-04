package com.example.pillee.jetpackcompnavigation.alarms

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.*


class AlarmCreator() {
    var alarmManager: AlarmManager? = null



    @Composable
    fun createAlarms(days : String, hour : String){
        var delimiter = ":"
        val context= LocalContext.current
        val parts = hour.split(delimiter)
        val intent = Intent(context, SampleBootReceiver::class.java)




        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, parts[0].toInt())
            set(Calendar.MINUTE, parts[1].toInt())
        }

        this.alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val inte = Intent(context, SampleBootReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, Date().seconds, inte, PendingIntent.FLAG_IMMUTABLE)
        alarmManager?.setInexactRepeating(AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent)




    }

}