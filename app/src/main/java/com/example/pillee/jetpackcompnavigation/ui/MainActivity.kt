package com.example.pillee.jetpackcompnavigation.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pillee.jetpackcompnavigation.navigation.AppNavigation
import com.example.pillee.jetpackcompnavigation.screens.PillDetailScreen
import com.example.pillee.jetpackcompnavigation.screens.viewmodels.PillDetailViewModel
import com.example.pillee.themes.PilleeTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private val auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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