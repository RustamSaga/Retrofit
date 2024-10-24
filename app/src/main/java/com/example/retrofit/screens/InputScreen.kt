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
import com.example.retrofit.navigationUtils.BottomNavItem
import com.example.retrofit.navigationUtils.MyBottomNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InputScreen(
    contentProduct: (String) -> Unit = {}
) {

    Scaffold() {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.padding(6.dp),
                onClick = {
                    contentProduct(BottomNavItem.LOGIN)
                }
            ) {
                Text(text = "Login Screen")
            }
            Button(
                modifier = Modifier.padding(6.dp),
                onClick = {
                    contentProduct(BottomNavItem.WEATHER)
                }
            ) {
                Text(text = "Weather Screen")
            }

        }
    }

}