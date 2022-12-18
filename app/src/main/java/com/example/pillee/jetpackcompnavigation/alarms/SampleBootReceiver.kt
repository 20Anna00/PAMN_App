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


        var roomdb = NotificationRoomDatabase.getDatabase(context)
        val time = System.currentTimeMillis()
        var title = ""
        vibrator.vibrate(500)
        val thread = Thread{
            var notificationList = roomdb.accessDao().getAlphabetizedNotifications()
            Log.d("PILLL",notificationList.toString())
            for(notification in notificationList){
                if(notification.dateMillis >= time-120000 && notification.dateMillis <= time+120000) {
                    title = notification.pill
                    Log.d("PILLL",title)
                    var notification = NotificationCreator()
                    notification.createNotification(context,intent,"Hi, it is time to take a pill","The pill you need to take is: $title")

                }
            }



        }
        thread.start()

        }





}
