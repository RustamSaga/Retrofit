package com.example.retrofit.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import coil.compose.AsyncImage
import com.example.retrofit.data.User
import com.example.retrofit.ui_components.ProgressBar
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition")
@Composable
fun LoginScreen(
    user: StateFlow<User>,
    viewModel: LoginViewModel = hiltViewModel(),
    onChangeUser: (User) -> Unit
) {

    val usernameState = remember { mutableStateOf("hbingley1") }
    val passwordState = remember { mutableStateOf("CQutx25i8r") }
    val errorState = remember {
        mutableStateOf("")
    }
    val enableNextButton = remember {
        mutableStateOf(false)
    }
    val enableLoginButton = remember {
        mutableStateOf(true)
    }
    val enabledTextField = remember {
        mutableStateOf(true)
    }
    val enabledProgressBar = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = errorState.value,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Red
        )

        Card(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp)
        ) {
            AsyncImage(
                model = user.value.image,
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier.padding(4.dp),
            text = user.value.firstName,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = user.value.lastName,
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            enabled = enabledTextField.value,
            modifier = Modifier.padding(4.dp),
            value = usernameState.value,
            onValueChange = { usernameState.value = it }
        )
        OutlinedTextField(
            enabled = enabledTextField.value,
            modifier = Modifier.padding(4.dp),
            value = passwordState.value,
            onValueChange = { passwordState.value = it }
        )
        Button(
            enabled = enableLoginButton.value,
            onClick = {
                enableLoginButton.value = false
                enabledTextField.value = false
                enabledProgressBar.value = true
                viewModel.auth(
                    username = usernameState.value,
                    password = passwordState.value,
                ) { user, error, enabledButton ->
                    onChangeUser(user?: User())
                    errorState.value = error
                    enableNextButton.value = enabledButton
                    enableLoginButton.value = true
                    enabledTextField.value = true
                    enabledProgressBar.value = false
                }
            }
        ) {
            Text(text = "Login")
        }

        Button(
            enabled = enableNextButton.value,
            onClick = {
                viewModel.onNavigateToSingleScreen()
            }) {
            Text(text = "Next")
        }

        ProgressBar(isDisplayed = enabledProgressBar.value)

    }
}