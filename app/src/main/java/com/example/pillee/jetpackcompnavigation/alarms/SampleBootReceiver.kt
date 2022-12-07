package com.example.pillee.jetpackcompnavigation.alarms

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent


class SampleBootReceiver : BroadcastReceiver() {


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

        val i = Intent()
        i.setClassName("com.example.pillee.jetpackcompnavigation.ui", "NotifActivity")
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(i)


    }





}
