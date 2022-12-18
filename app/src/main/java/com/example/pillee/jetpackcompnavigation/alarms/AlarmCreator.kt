
package com.example.pillee.jetpackcompnavigation.alarms

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.pillee.jetpackcompnavigation.model.Notification
import com.example.pillee.jetpackcompnavigation.model.repository.AuthRepository
import com.example.pillee.jetpackcompnavigation.model.repository.NotificationRepository
import java.util.*


class AlarmCreator(days: String, hours: String, pillName: String) {
    private var alarmManager: AlarmManager? = null
    private var dayList = splitDays(days)
    private var hourList = splitHours(hours)
    private val pillName = pillName
    private val daysMap = mapOf("Monday" to 1 ,"Tuesday" to 2 ,"Wednesday" to 3 ,"Thursday" to 4 ,"Friday" to 5 ,"Saturday" to 6 ,"Sunday" to  7)
    private fun splitDays (days:String) : List<String> = days.split(",")
    private fun splitHours (hours:String) : List<String> = hours.split(",")
    private fun splitHourMinutes (time:String) : List<String> = time.split(":")

    private fun createCalendar (hour : String, minutes : String, day : String) : Long {
        val calendar: Calendar = Calendar.getInstance()
        val now = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, hour.toInt())
        calendar.set(Calendar.MINUTE,minutes.toInt())
        calendar.set(Calendar.DAY_OF_WEEK, this.daysMap.getValue(day))

       /** This checks if it is necessary to add one day
        * Link reference :  https://stackoverflow.com/questions/62900266/android-studio-set-alarm-at-specific-hour-and-minute-with-sound
        */

        if( now > calendar.timeInMillis ) calendar.add( Calendar.DAY_OF_MONTH, 1 )
        Log.d("Hola", calendar.timeInMillis.toString())
        return calendar.timeInMillis
    }

    @Composable
    fun createAlarms( ){
        val context= LocalContext.current
        val repository: AuthRepository = AuthRepository()
        val notificationRepository: NotificationRepository = NotificationRepository()

        this.alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(context, SampleBootReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context,0, intent,0)

        for (day in dayList)
            for (time in hourList){

                Log.d("HORAAA",time)
                Log.d("DIIIA",day)
                val calendar = createCalendar(splitHourMinutes(time)[0],splitHourMinutes(time)[1],day)
                alarmManager?.setRepeating(AlarmManager.RTC_WAKEUP, calendar, 24 * 7 * 60 * 60 * 1000, pendingIntent)
                //notificationRepository.addNotificationToFirestore(Notification(repository.currentUser!!.uid,calendar,pillName))

            }
    }
}

