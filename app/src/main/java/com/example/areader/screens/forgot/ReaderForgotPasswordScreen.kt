package com.example.areader.screens.forgot

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Preview
@Composable
fun ForgotPasswordScreen(navController: NavController = NavController(LocalContext.current)) {
    Surface(
        Modifier
            .fillMaxSize()
    ) {
        // set background by bitmap
        Image(
            bitmap = ImageBitmap
                .imageResource(
                    id = R.drawable.background_image
                ),
            contentDescription = "image background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserForgotForm(navController = navController)
            }
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForgotForm(
    loading: Boolean = false,
    navController: NavController,
    onDone: (String) -> Unit = { email ->
    }
) {
    val context = LocalContext.current

    // save state when user using other task
    val email = rememberSaveable { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value) {
        email.value.trim().isNotEmpty()
    }

    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TitleHeader("Forgot\nPassword")

        EmailInput(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            emailState = email,
            enabled = !loading,
            onAction = KeyboardActions(onDone = { keyboardController?.hide() }) {
                onDone(email.value.trim())
            }
        )

        SubmitButton(
            textId = "Send",
            loading = loading,
            validInput = valid,
            colorButton = Color(1, 102, 255, 255)
        ) {
            Toast.makeText(context, "Check our link in your email!", Toast.LENGTH_LONG).show()
            onDone(email.value.trim())
            keyboardController?.hide()
        }
    }
}