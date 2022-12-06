package com.example.pillee.jetpackcompnavigation.alarms

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
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
        this.alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

        val calendar: Calendar = Calendar.getInstance()

        calendar[Calendar.HOUR_OF_DAY] = 21
        calendar[Calendar.MINUTE] = 20

        val intent = Intent(context, SampleBootReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context,0, intent,0)

        //alarmManager?.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,60000, pendingIntent)

        alarmManager?.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        //val pendingIntent = PendingIntent.getBroadcast(context, Date().seconds, intent, PendingIntent.FLAG_IMMUTABLE)


    }
}