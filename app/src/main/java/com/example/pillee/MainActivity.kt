package com.example.pillee

import android.content.Intent
import android.content.res.Resources.Theme
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.android.synthetic.main.activity_main.*

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.pillee.databinding.ActivityMainBinding
import com.example.pillee.jetpackcompnavigation.navigation.AppNavigation
import com.example.pillee.themes.PilleeTheme
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            PilleeTheme{
                Surface(color = MaterialTheme.colors.background){
                    AppNavigation()
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComponent(){
    AppNavigation()
}