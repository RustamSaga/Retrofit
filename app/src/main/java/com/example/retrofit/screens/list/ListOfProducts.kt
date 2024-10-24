package com.example.retrofit.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.retrofit.ui_components.ProductItem
import com.example.retrofit.data.User
import com.example.retrofit.data.retrofit.Products
import com.example.retrofit.ui_components.ProgressBar
import kotlinx.coroutines.flow.StateFlow


@Composable
fun ListOfProducts(
    user: StateFlow<User>,
    viewModel: ListViewModel = hiltViewModel()
) {

    val productsState = remember {
        mutableStateOf(Products())
    }
    val enabledProgressBar = remember {
        mutableStateOf(false)
    }

    val errorState = remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ProgressBar(isDisplayed = enabledProgressBar.value)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .padding(4.dp)
        ) {
            items(productsState.value.products) { product ->
                ProductItem(
                    title = product.title,
                    description = product.description
                )
            }
        }

        Button(modifier = Modifier.padding(4.dp), onClick = {
            viewModel.getAllProducts(user.value) { products, error ->
                productsState.value = products?: Products()
                errorState.value = error?: ""
            }
        }) {
            Text(text = "GET ALL")
        }
        Text(text = errorState.value, style = MaterialTheme.typography.titleMedium)

        Text(modifier = Modifier.padding(4.dp), text = "total = " + productsState.value.total)
        Text(modifier = Modifier.padding(4.dp), text = "limit = " + productsState.value.limit)
        Text(modifier = Modifier.padding(4.dp), text = "skip = " + productsState.value.skip)
    }
}