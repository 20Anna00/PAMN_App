package com.example.pillee.jetpackcompnavigation.ui
import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pillee.jetpackcompnavigation.navigation.AppNavigation
import com.example.pillee.themes.PilleeTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            PilleeTheme{
                Surface(color = MaterialTheme.colors.background){
                    AppNavigation(auth)
                }

        }

    }

}
@Composable
fun MyComponent(){
    Row(){
        MyImage()
        MyText("hola jetpack compose")
    }
}

@Composable
fun MyText(text: String){
    Text(text = text)

}
@Composable
fun MyTexts(){
    Column() {
        MyText(text = "Hola")
        MyText(text = "Esto es una prueba")

    }

}

@Composable
fun MyImage (){
    Image(
        painterResource(R.drawable.arrow_up_float),"Mi imagen"
    )
}
}

@Preview(showBackground = true)
@Composable
fun PreviewComponent(){
    AppNavigation(Firebase.auth)
}