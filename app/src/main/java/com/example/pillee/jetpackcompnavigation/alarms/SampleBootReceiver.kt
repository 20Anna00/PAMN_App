package com.example.pillee.jetpackcompnavigation.alarms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Vibrator
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.pillee.jetpackcompnavigation.model.repository.AuthRepository
import com.example.pillee.jetpackcompnavigation.roomDB.NotificationDB

import com.example.pillee.jetpackcompnavigation.roomDB.NotificationRoomDatabase


class SampleBootReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onReceive(context: Context, intent: Intent) {
        val vibrator = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(500)

        val time = System.currentTimeMillis()

        Log.d("HOLAAAAA","Holaaaaa")

        var roomdb = NotificationRoomDatabase.getDatabase(context)
        var title = ""
        val thread = Thread{
            var notificationList = roomdb.accessDao().getAlphabetizedNotifications()
            for(notification in notificationList){
                val time = System.currentTimeMillis()
                if(notification.dateMillis >= time-60000 && notification.dateMillis <= time+60000) {
                    title = notification.pill
                }
            }
            var notification = NotificationCreator()
            notification.createNotification(context,intent,"Hi, it is time to take a pill","The pill you need to take is: $title")

        }
        thread.start()
        }





}
