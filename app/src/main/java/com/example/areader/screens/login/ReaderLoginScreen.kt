package com.example.areader.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Preview
@Composable
fun LoginScreen(navController: NavController = NavController(LocalContext.current)) {
    Surface(
        Modifier
            .fillMaxSize()
            .padding(top = 34.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to A.Reader",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Red.copy(0.5f)
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                value = "username",
                onValueChange = {},
                singleLine = true,
                enabled = true,

            )
        }

    }

}