package com.example.pillee.jetpackcompnavigation.screens.register

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pillee.jetpackcompnavigation.model.repository.AuthRepository
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.jetpackcompnavigation.screens.register.RegisterUiState
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository = AuthRepository()
): ViewModel(){

    val currentUser = repository.currentUser
    val hasUser: Boolean
        get() = repository.hasUser()

    var registerUiState by mutableStateOf(RegisterUiState())
        private set

    fun onNameChange(name:String) {
        registerUiState = registerUiState.copy(name = name)
    }

    fun onEmailChange(email: String) {
        registerUiState = registerUiState.copy(email = email)
    }

    fun onPasswordChange(password: String){
        registerUiState = registerUiState.copy(password = password)
    }

    private fun validateForm() =
        registerUiState.email.isNotBlank() && registerUiState.password.isNotBlank()

    fun RegisterUser(context: Context, navController: NavController) = viewModelScope.launch {
        try {
            if(!validateForm()){
                Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
            repository.createUser(registerUiState.email, registerUiState.password){
                    isSuccessful -> if(isSuccessful){
                navController.navigate(route = AppScreens.StartPageScreen.route)
                Toast.makeText(context, "Success Register", Toast.LENGTH_SHORT).show()
            }else{

                Toast.makeText(context, "Failed Register", Toast.LENGTH_SHORT).show()
            }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }


}

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
)