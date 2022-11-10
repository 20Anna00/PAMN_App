package com.example.pillee

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.mailfield
import kotlinx.android.synthetic.main.activity_login.passwordfield
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setup()
    }

    private fun setup (){
        title = "Register"
        registerbutton.setOnClickListener {
            if (mailfield.text.isNotEmpty() && passwordfield.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(mailfield.text.toString(),
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