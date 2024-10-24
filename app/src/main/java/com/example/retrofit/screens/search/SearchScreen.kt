package com.example.retrofit.screens.search

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.retrofit.data.User
import com.example.retrofit.data.retrofit.Products
import com.example.retrofit.ui_components.ProgressBar
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SearchScreen(
    user: StateFlow<User>,
    context: Context,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val text = remember {
        mutableStateOf("")
    }
    val searchProducts = remember {
        mutableStateOf(Products())
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

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            Text(
                text = errorState.value,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Red
            )
            LazyColumn {
                items(searchProducts.value.products) {
                    Card(
                        modifier = Modifier.padding(4.dp),
                        border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.onBackground)
                    ) {
                        ProgressBar(isDisplayed = enabledProgressBar.value)
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "id: ${it.id},\n" +
                                    "title: ${it.title},\n" +
                                    "description: ${it.description},\n" +
                                    "price: ${it.price},\n" +
                                    "discountPercentage: ${it.discountPercentage},\n" +
                                    "rating: ${it.rating},\n" +
                                    "stock: ${it.stock},\n" +
                                    "brand: ${it.brand},\n" +
                                    "category: ${it.category},\n" +
                                    "thumbnail: ${it.thumbnail}"
                        )
                    }

                }
            }

        }

        OutlinedTextField(
            modifier = Modifier.padding(4.dp),
            value = text.value,
            onValueChange = {
                text.value = it
            }
        )
        Button(onClick = {
            if (text.value.isBlank()) {
                Toast.makeText(
                    context,
                    "field is blank",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                enabledProgressBar.value = true
                viewModel.search(user.value, text.value) { products, error ->
                    enabledProgressBar.value = false
                    searchProducts.value = products
                    errorState.value = error
                }
            }
        }) {
            Text(text = "Get")
        }
    }
}