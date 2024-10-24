package com.example.retrofit.navigationUtils

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.retrofit.screens.MainViewModel

@Composable
fun MyBottomNavigation(
    viewModel: MainViewModel = hiltViewModel()
) {
    var selectedItem by remember { mutableIntStateOf(0) }

    val items = listOf(
        BottomNavItem.Single,
        BottomNavItem.List,
        BottomNavItem.Search
    )
    NavigationBar(
        contentColor = Color.Black
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    viewModel.navigate(item.screen_route)
                }
            )
        }
    }
}