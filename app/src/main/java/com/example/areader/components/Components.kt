package com.example.areader.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.areader.R
import com.example.areader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

// complete
@Composable
fun TitleHeader(headerText: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = headerText,
            color = Color(113, 92, 248, 255),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
        )
    }
}

// complete
@Composable
fun ReaderLogo() {
    Text(
        text = "A.Reader",
        modifier = Modifier.padding(bottom = 16.dp),
        style = MaterialTheme.typography.h3,
        color = Color.Red.copy(0.5f)
    )
}

// well email input version
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email ID",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = emailState.value,
        onValueChange = { emailState.value = it },
        label = { Text(text = labelId) },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.AlternateEmail,
                contentDescription = "Icon Login"
            )
        },
        keyboardActions = onAction
    )
}

// well password input version
@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String = "Password",
    enable: Boolean,
    passwordVisibility: MutableState<Boolean>,
    onAction: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Done
) {
    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        label = { Text(text = labelId) },
        enabled = enable,
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        trailingIcon = {
            PasswordVisibility(passwordVisibility = passwordVisibility)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.VpnKey,
                contentDescription = "Icon Login"
            )
        },
        keyboardActions = onAction
    )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value

    val image = if (visible)
        Icons.Filled.Visibility
    else Icons.Filled.VisibilityOff

    val description = if (visible) "Hide password" else "Show password"

    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icon(imageVector = image, description)
    }
}

// well button version
@Composable
fun SubmitButton(
    modifier: Modifier = Modifier,
    textId: String,
    loading: Boolean,
    validInput: Boolean,
    colorButton: Color = Color.Blue,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = !loading && validInput,
        shape = CircleShape,
        modifier = modifier
            .fillMaxWidth(0.6f)
            .padding(
                start = 4.dp,
                end = 4.dp
            ),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorButton)
    ) {
        if (loading) CircularProgressIndicator(Modifier.fillMaxSize(0.1f))
        else
            Text(
                text = textId,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
    }
}

// update later
@Composable
fun CreateAccount(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Text(text = "Don't have account? ")
        Text(
            text = "Create account",
            modifier = Modifier.clickable {
                navController.navigate(ReaderScreens.CreateAccountScreen.name)
            },
            color = Color.Blue.copy(0.7f)
        )
    }
}

// coming soon
@Composable
fun OtherLogin() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        Text(
            text = "Or, login with...",
            fontSize = 10.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Preview
// update later
@Composable
fun OptionsPassword(navController: NavController = NavController(LocalContext.current)) {
    var rememberPassword by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)
    ) {
        IconButton(
            onClick = {
                rememberPassword = !rememberPassword
            },
        ) {
            Icon(
                imageVector = if (!rememberPassword) Icons.Filled.CheckBoxOutlineBlank else Icons.Filled.CheckBox,
                contentDescription = "Remember or not your account",
            )
        }

        Text(text = "Remember me")

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Forgot Password",
                modifier = Modifier
                    .clickable {
                        navController.navigate(ReaderScreens.ForgotPasswordScreen.name)
                    }
                    .padding(end = 5.dp),
                color = Color.Blue,
            )
        }

    }
}

// well name input version
@Composable
fun NameInput(
    modifier: Modifier = Modifier,
    nameState: MutableState<String>,
    labelId: String = "Full Name",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = nameState.value,
        onValueChange = { nameState.value = it },
        label = { Text(text = labelId) },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Icon Login"
            )
        },
        keyboardActions = onAction
    )
}


@Composable
fun ReaderHomeAppBar(
    title: String,
    showProfile: Boolean = true,
    navController: NavController
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showProfile) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Icon Logo",
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(12.dp)
                            )
                    )
                }

                Text(
                    text = title,
                    color = Color.Red.copy(0.7f),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
            }
        },
        actions = {
            IconButton(onClick = {
                FirebaseAuth.getInstance().signOut().run {
                    navController.navigate(ReaderScreens.LoginScreen.name)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "Logout Icon",
                    tint = Color.Red.copy(0.7f)
                )
            }
        },
        backgroundColor = Color.White,
        elevation = 0.dp
    )
}

@Composable
fun FABContent(onTab: (String) -> Unit) {
    FloatingActionButton(
        onClick = { onTab("") },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color(0xFF2CACE6)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add a book",
            tint = Color.White
        )
    }
}

@Composable
fun UserProfile(
    nameProfile: String,
    navController: NavController
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Profile",
                tint = MaterialTheme.colors.secondaryVariant,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        navController.navigate(ReaderScreens.StatsScreen.name)
                    }
            )
            Text(
                text = nameProfile,
                fontSize = 17.sp,
                color = Color.Red.copy(0.8f)
            )
        }
    }
}

@Composable
fun TitleSelection(modifier: Modifier = Modifier, label: String) {
    Surface() {
        Text(
            text = label,
            textAlign = TextAlign.Left,
            fontSize = 19.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun BackGroundWithImage(content: @Composable () -> Unit) {
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
            content()
        }
    }
}


@Composable
fun BookRating(score: Double) {
    Surface(
        shape = RoundedCornerShape(30.dp),
        elevation = 6.dp,
        modifier = Modifier.size(height = 60.dp, width = 30.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.StarBorder,
                contentDescription = "Start",
            )

            Text(
                text = score.toString(),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun RoundedButton(
    label: String = "Reading",
    radius: Int = 29,
    onPress: () -> Unit = {},

    ) {
    Surface(
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStartPercent = radius,
                bottomEndPercent = radius
            )
        ),
        color = Color(0xFF2094F0)
    ) {
        Column(
            modifier = Modifier
                .width(80.dp)
                .heightIn(30.dp)
                .clickable { onPress.invoke() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = TextStyle(color = Color.White, fontSize = 15.sp)
            )
        }
    }
}

@Composable
fun ReaderSearchAppBar(
    title: String,
    showProfile: Boolean = true,
    navController: NavController
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = Color.Red.copy(0.7f),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
                navController.navigate(ReaderScreens.HomeScreen.name)
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Logout Icon")
            }
        },
        backgroundColor = Color.White,
        elevation = 0.dp
    )
}

@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    nameState: MutableState<String>,
    labelId: String = "Search",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
) {


    OutlinedTextField(
        value = nameState.value,
        onValueChange = { nameState.value = it },
        label = { Text(text = labelId) },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Icon Search"
            )
        },
        keyboardActions = onAction
    )

}






