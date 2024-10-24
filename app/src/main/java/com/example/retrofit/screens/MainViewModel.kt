package com.example.retrofit.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.retrofit.data.User
import com.example.retrofit.navigationUtils.AppNavigator
import com.example.retrofit.navigationUtils.BottomNavItem
import com.example.retrofit.data.retrofit.Product
import com.example.retrofit.data.retrofit.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {


    private val _product = mutableStateOf(Product.build())
    val product: State<Product> = _product

    private val _products = MutableStateFlow(Products())
    val products: StateFlow<Products> = _products.asStateFlow()

    private val _userState = MutableStateFlow(User())
    val userState: StateFlow<User> = _userState.asStateFlow()

    private val _visibilityLoginScreen = MutableStateFlow(true)
    val visibilityLoginScreen: StateFlow<Boolean> = _visibilityLoginScreen.asStateFlow()

    val navigationChannel = appNavigator.navigationChannel



    fun setUser(user: User) {
        _userState.value = user
    }

    fun onNavigateToSearchScreen() {
        appNavigator.tryNavigateTo(BottomNavItem.SEARCH)
    }

    fun navigate(route: String) {
        appNavigator.tryNavigateTo(route)
    }

}