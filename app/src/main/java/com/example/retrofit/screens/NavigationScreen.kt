package com.example.retrofit.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofit.data.User
import com.example.retrofit.navigationUtils.MyBottomNavigation
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(
    user: StateFlow<User>,
    contentProduct: @Composable () -> Unit = {}
) {

    val firstScreen = remember {
        val firstScreen: FirstScreen = FirstScreen.Nothing
        mutableStateOf(firstScreen)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "${user.value.firstName} " +
                            user.value.lastName
                )
            })
        },
        bottomBar = {
            MyBottomNavigation()
        }) {

        contentProduct.invoke()

    }
}


open class FirstScreen {
    object WeatherScreen : FirstScreen()
    object LoginScreen : FirstScreen()
    object Nothing : FirstScreen()
}