package com.example.pillee.jetpackcompnavigation.model.repository

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.alarms.NotificationCreator
import com.example.pillee.jetpackcompnavigation.model.DataOrException
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.example.pillee.jetpackcompnavigation.ui.MainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.security.AccessControlContext
import java.security.AccessController.getContext


class PillRepository (){

    val currentUser = Firebase.auth.currentUser?.uid.orEmpty()


    private val pillList: CollectionReference = Firebase.firestore.collection("pills")

    fun addNewPill (pill: Pills, context: Context){
        try {
            pillList.document(pill.id).set(pill)
            Toast.makeText(context, "Added pill successfully", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    suspend fun getPillList(): DataOrException<List<Pills>, Exception> {
        val dataOrException = DataOrException<List<Pills>, Exception>()

        try{
            dataOrException.data = pillList.whereEqualTo("userID", currentUser)
                .get().await().map {document
                -> document.toObject(Pills::class.java)

            }
        }
        catch (e: FirebaseFirestoreException){
            dataOrException.e  = e
        }
        return dataOrException

    }

    fun updatePill(newNumber : String, pillId: String, pillName: String, currentPills: String){
        if (currentPills.toInt() == 4){

            notification(pillname = pillName)



        }
        try{
            val map = mapOf(
                "daysRefill" to newNumber
            )
            pillList.document(pillId).update(map)

        }
        catch(e : Exception){
            e.printStackTrace()
        }
    }

    fun deletePill(pillId : String){
        try{
            pillList.document(pillId).delete()
        } catch(e: Exception){
            e.printStackTrace()
        }
    }

    fun notification(pillname: String){
        var notificationManager: NotificationManagerCompat? = null
        val context = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val contentIntent = PendingIntent.getActivity(
            ApplicationProvider.getApplicationContext<Context>(),
            0,
            Intent(),  // add this
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notification = NotificationCompat.Builder(context, "com.ebookfrenzy.notifydemo.news")
            .setContentTitle("You might need to refill the pill")
            .setContentText("There are only 4 pills of " + pillname)
            .setSmallIcon(R.drawable.pillelogo)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(contentIntent)
            .setSound(uri)
            .build()



        notificationManager = context?.let { NotificationManagerCompat.from(it) }
        //notification?.let { taskInfo?.let { it1 -> notificationManager?.notify(it1.id, it) } }

        //Setting a random to the id of the notification bc they need different ids
        notificationManager?.notify((0..1000).random(), notification)
    }
}

