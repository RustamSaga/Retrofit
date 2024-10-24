package com.example.retrofit.screens.simple

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.retrofit.data.User
import com.example.retrofit.data.retrofit.Product
import com.example.retrofit.ui_components.ProgressBar

@Composable
fun SimpleProduct(
    user: User,
    viewModel: SimpleViewModel = hiltViewModel()
) {

    val productState = remember {
        mutableStateOf(Product.build())
    }

    val errorState = remember {
        mutableStateOf("")
    }

    val enabledProgressBar = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = errorState.value, style = MaterialTheme.typography.titleMedium)

        Button(modifier = Modifier.padding(6.dp), onClick = {
            enabledProgressBar.value = true
            viewModel.getProduct(user) { product, error ->
                product?.let {
                    productState.value = it
                }
                error?.let {
                    errorState.value = it
                }
                enabledProgressBar.value = false

            }
        } ) {
            Text(text = "GET", fontSize = 24.sp)
        }

        Text(text = "Single product = ${productState.value}")
        ProgressBar(isDisplayed = enabledProgressBar.value)


    }
}