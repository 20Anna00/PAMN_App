package com.example.pillee.jetpackcompnavigation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.PasswordAuthentication

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository()
): ViewModel() {
    val currentUser = repository.currentUser
    val hasUser: Boolean
        get() = repository.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(email: String) {
        loginUiState = loginUiState.copy(email = email)
    }

    fun onPasswordChange(password: String){
        loginUiState = loginUiState.copy(password = password)
    }

    private fun validateForm() =
        loginUiState.email.isNotBlank() && loginUiState.password.isNotBlank()

    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if(!validateForm()){
                Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
            repository.login(loginUiState.email, loginUiState.password){
                isSuccessful -> if(isSuccessful){
                    Toast.makeText(context, "Success Login", Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(context, "Failed Login", Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isSuccessLogin: Boolean = false

)