package com.example.retrofit.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.User
import com.example.retrofit.navigationUtils.AppNavigator
import com.example.retrofit.navigationUtils.BottomNavItem
import com.example.retrofit.data.retrofit.AuthRequest
import com.example.retrofit.domain.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun auth(
        username: String,
        password: String,
        result: (User?, error: String, enabledButton: Boolean) -> Unit,
    ) {
        viewModelScope.launch {

            try {
                val def = async(Dispatchers.IO) {
                    authUseCase.auth(
                        AuthRequest(
                            username = username,
                            password = password
                        )
                    )
                }
                def.await().onSuccess { user ->
                    result(user, "", true)
                }.onFailure {
                    result(null, it.message ?: "", false)
                }
            } catch (e: Exception) {
                result(null, e.message?: "", false)

            }


        }
    }

    fun onNavigateToSingleScreen() {
        appNavigator.tryNavigateTo(BottomNavItem.SINGLE)
    }

}