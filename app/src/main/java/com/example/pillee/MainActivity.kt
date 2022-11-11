package com.example.pillee
import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{

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
@Preview
@Composable
fun PreviewComponent(){
    MyComponent()
}