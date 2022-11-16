package com.example.pillee.jetpackcompnavigation.screens.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.jetpackcompnavigation.model.repository.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel
@Inject
constructor( private val repository: AuthRepository = AuthRepository()): ViewModel() {
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

    fun loginUser(context: Context, navController: NavController) = viewModelScope.launch {
        try {
            if(!validateForm()){
                Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
            println("Aqui llega")
            repository.login(loginUiState.email, loginUiState.password){
                isSuccessful -> if(isSuccessful){
                    navController.navigate(route = AppScreens.StartPageScreen.route)
                    Toast.makeText(context, "Success Login", Toast.LENGTH_SHORT).show()
                }else{

                    Toast.makeText(context, "Failed Login", Toast.LENGTH_SHORT).show()
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
)