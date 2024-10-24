package com.example.retrofit.screens.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {

    val tempState = remember {
        mutableStateOf("")
    }
    val timeState = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(modifier = Modifier.padding(6.dp), text = tempState.value)
        Text(modifier = Modifier.padding(6.dp), text = timeState.value)
        Button(modifier = Modifier.padding(6.dp), onClick = {
            viewModel.get{ temp, date ->
                tempState.value = temp
                timeState.value = date
            }
        }) {
            Text(text = "GET")
        }
    }
}