package com.example.areader.screens.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.areader.R
import com.example.areader.components.*
import com.example.areader.navigation.ReaderScreens

@Composable
fun LoginScreen(
    navController: NavController = NavController(LocalContext.current),
    viewModel: LoginScreenViewModel
) {
    BackGroundWithImage {
        UserLoginForm(
            loading = false,
            navController = navController
        ) { email, password ->
            viewModel.signInWithEmailAndPassword(email, password) {
                navController.popBackStack()
                navController.navigate(ReaderScreens.HomeScreen.name)
            }
        }
    }

}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserLoginForm(
    loading: Boolean = false,
    navController: NavController,
    onDone: (String, String) -> Unit = { email, password ->
    }
) {
    // save state when user using other task
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    // help show and hide password
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequest = FocusRequester.Default

    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleHeader("Login")

        EmailInput(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            emailState = email,
            enabled = !loading,
            onAction = KeyboardActions(onNext = { passwordFocusRequest.requestFocus() }) {
                onDone(email.value.trim(), password.value.trim())
            }
        )

        PasswordInput(
            modifier = Modifier
                .focusRequester(passwordFocusRequest)
                .padding(start = 10.dp, end = 10.dp),
            passwordState = password,
            enable = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions(onDone = { keyboardController?.hide() }) {
                onDone(email.value.trim(), password.value.trim())
            }
        )

        OptionsPassword(navController)

        SubmitButton(
            textId = "Login",
            loading = loading,
            validInput = valid,
            colorButton = Color(1, 102, 255, 255)
        ) {
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }

        CreateAccount(navController)

        OtherLogin()
    }
}














