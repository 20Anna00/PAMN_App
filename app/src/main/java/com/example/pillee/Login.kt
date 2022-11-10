package com.example.pillee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setup()
    }

    private fun setup (){
        title = "Login"
        loginbutton.setOnClickListener {
            if (mailfield.text.isNotEmpty() && passwordfield.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(mailfield.text.toString(),
                    passwordfield.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this, "Hola, salio bien", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Cound't make login", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}