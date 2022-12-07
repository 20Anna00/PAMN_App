package com.example.pillee.jetpackcompnavigation.ui
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pillee.jetpackcompnavigation.alarms.AlarmCreator
import com.example.pillee.jetpackcompnavigation.navigation.AppNavigation
import com.example.pillee.jetpackcompnavigation.screens.PillDetailScreen
import com.example.pillee.jetpackcompnavigation.screens.viewmodels.PillDetailViewModel
import com.example.pillee.themes.PilleeTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {
    private val auth = Firebase.auth
    private var notificationManager: NotificationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val alarmCreator = AlarmCreator()
        //Notifications
        notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        CreateNotificationChannel(
            "com.ebookfrenzy.notifydemo.news",
            "NotifyDemo News",
            "Example News Channel")

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // FCM SDK (and your app) can post notifications.
            } else {
                // TODO: Inform user that that your app will not show notifications.
            }
        }

        setContent {

          PilleeTheme {
              Surface(color = MaterialTheme.colors.background) {

                  alarmCreator.createAlarms(days = "tuesday", hour ="12:50" )
                  AppNavigation()
              }
          }

        }

    }
    @Composable
    fun PreviewComponent() {
        //AppNavigation()

    }

    private fun CreateNotificationChannel(id: String, name: String,
                                          description: String) {

        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id, name, importance)

        channel.description = description
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.vibrationPattern =
            longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        notificationManager?.createNotificationChannel(channel)
    }





}