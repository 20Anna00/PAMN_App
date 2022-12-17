package com.example.pillee.jetpackcompnavigation.ui
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.pillee.jetpackcompnavigation.alarms.AlarmCreator
import com.example.pillee.jetpackcompnavigation.navigation.AppNavigation
import com.example.pillee.themes.PilleeTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : ComponentActivity() {
    private val auth = Firebase.auth
    private var notificationManager: NotificationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Notifications
        notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val att = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()
        val channel = NotificationChannel(
            "com.ebookfrenzy.notifydemo.news",
            "NotifyDemo News",
            NotificationManager.IMPORTANCE_HIGH)
        channel.description = "HOLAA"
        channel.setSound(uri, att)
        notificationManager!!.createNotificationChannel(channel)

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // FCM SDK (and your app) can post notifications.
            } else {
                // TODO: Inform user that that your app will not show notifications.
            }
        }

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        setContent {

          PilleeTheme {
              Surface(color = MaterialTheme.colors.background) {


                  AppNavigation()
              }
          }

        }

    }
    @Composable
    fun PreviewComponent() {
        //AppNavigation()

    }







}