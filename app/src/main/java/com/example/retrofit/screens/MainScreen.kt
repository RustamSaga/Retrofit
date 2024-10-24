package com.example.retrofit.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.retrofit.navigationUtils.BottomNavItem
import com.example.retrofit.navigationUtils.NavHost
import com.example.retrofit.navigationUtils.NavigationIntent
import com.example.retrofit.navigationUtils.composable
import com.example.retrofit.screens.list.ListOfProducts
import com.example.retrofit.screens.login.LoginScreen
import com.example.retrofit.screens.search.SearchScreen
import com.example.retrofit.screens.simple.SimpleProduct
import com.example.retrofit.screens.weather.WeatherScreen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun MainScreen(
    context: Context,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavigationEffects(
        navigationChannel = viewModel.navigationChannel,
        navHostController = navController
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.FIRST_SCREEN
        ) {
            composable(destination = BottomNavItem.LOGIN) {
                LoginScreen(viewModel.userState) {
                    viewModel.setUser(it)
                }
            }
            composable(destination = BottomNavItem.LIST) {
                NavigationScreen(viewModel.userState) {
                    ListOfProducts(viewModel.userState)
                }
            }
            composable(destination = BottomNavItem.SINGLE) {
                NavigationScreen(viewModel.userState) {
                    SimpleProduct(viewModel.userState.value)
                }
            }
            composable(destination = BottomNavItem.SEARCH) {
                NavigationScreen(viewModel.userState) {
                    SearchScreen(viewModel.userState, context)
                }
            }
            composable(destination = BottomNavItem.WEATHER) {
                WeatherScreen()
            }
            composable(destination = BottomNavItem.FIRST_SCREEN){
                InputScreen {
                    viewModel.navigate(it)
                }
            }
        }
    }

}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }

                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}