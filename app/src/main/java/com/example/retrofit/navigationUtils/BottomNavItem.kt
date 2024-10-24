package com.example.retrofit.navigationUtils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var screen_route: String
) {

    object Login : BottomNavItem("Login", Icons.Default.Lock, this.LOGIN)
    object Single : BottomNavItem("Single", Icons.Default.Info, this.SINGLE)
    object List : BottomNavItem("List", Icons.Default.List, this.LIST)
    object Search : BottomNavItem("Search", Icons.Default.Search, this.SEARCH)

    companion object {
        const val FIRST_SCREEN = "FirstScreen"
        const val WEATHER = "Weather"
        const val LOGIN = "login"
        const val SINGLE = "single"
        const val LIST = "list"
        const val SEARCH = "search"

    }
}