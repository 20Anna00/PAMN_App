package com.example.pillee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Texts()
        }



    }

    @Composable
    fun Texts(){
        Text(text = "Hola Jetpack Compose!")
    }

    @Preview
    @Composable
    fun PreviewTexts(){
        Texts()
    }

    fun buttons() {
        login_button.setOnClickListener {
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        }
        register_button.setOnClickListener {
            val intent = Intent(this@MainActivity, Register::class.java)
            startActivity(intent)
        }
    }
}