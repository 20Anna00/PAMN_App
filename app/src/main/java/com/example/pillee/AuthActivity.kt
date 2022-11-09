package com.example.pillee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

    private fun setup (){
        title = "Login"
        loginbutton.setOnClickListener {
            if (mailfield.text.isNotEmpty() && passwordfield.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(mailfield.text.toString(),
                    passwordfield.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){

                    }else{
                        Toast.makeText(this,"Cound't make login", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
    private fun showSchedule (email:String){
        val intent = Intent(this,ScheduleFragment::class.java).apply {
            putExtra("email", email)
        }

    }
}