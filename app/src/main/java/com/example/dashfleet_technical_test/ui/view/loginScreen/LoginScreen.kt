package com.example.dashfleet_technical_test.ui.view.loginScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(userLoginViewModel: UserLoginViewModel) {

    val userPhoneNumber by userLoginViewModel
        .userPhoneNumber
        .observeAsState(initial = "")

    val userPassword by userLoginViewModel
        .userPassword
        .observeAsState(initial = "")

    val isLoginButtonEnabled by userLoginViewModel
        .isLoginButtonEnabled
        .observeAsState(initial = false)

    Scaffold(
        topBar = {
            LoginTopBar()
        },
        modifier = Modifier.fillMaxSize()
    ) {

        Column() {
            PhoneNumberField(userPhoneNumber = userPhoneNumber) {
                userLoginViewModel.onLoginChanged(userPhoneNumber = it, userPassword = userPassword)
            }
            PasswordField(userPassord = userPassword) {
                userLoginViewModel.onLoginChanged(
                    userPhoneNumber = userPhoneNumber,
                    userPassword = it
                )
            }
            RememberUserToggle()
            LoginButton(isLoginButtonEnabled, userLoginViewModel)
        }
    }
}

@Composable
private fun LoginTopBar() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "DASHFLEET APP",
                color = Color.White,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Black
            )
        }
    }
}


@Composable
fun PhoneNumberField(userPhoneNumber: String, onTextChanged: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 45.dp, start = 15.dp, end = 15.dp)
    ) {
        Text(text = "Phone Number")
        OutlinedTextField(
            value = userPhoneNumber, onValueChange = {
                onTextChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray
            )
        )
    }
}

@Composable
fun PasswordField(userPassord: String, onTextChanged: (String) -> Unit) {
    var isPasswordVisible: Boolean by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 15.dp, end = 15.dp)
    ) {
        Text(text = "Password")
        OutlinedTextField(
            value = userPassord, onValueChange = {
                onTextChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray
            ),
            trailingIcon = {
                val passwordVisibilityIcon = if (isPasswordVisible) {
                    Icons.Outlined.VisibilityOff
                } else {
                    Icons.Outlined.Visibility
                }
                IconButton(onClick = {
                    isPasswordVisible = isPasswordVisible.not()
                }) {
                    Icon(
                        imageVector = passwordVisibilityIcon,
                        contentDescription = "Show password"
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
    }
}

@Composable
fun RememberUserToggle() {
    var rememberToggle: Boolean by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = Modifier.padding(top = 20.dp, start = 15.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Switch(
            checked = rememberToggle,
            onCheckedChange = { rememberToggle = !rememberToggle },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.DarkGray,
                checkedTrackColor = MaterialTheme.colors.primary,
                uncheckedThumbColor = Color.DarkGray,
            ),
        )
        Text(text = "Remember")
    }
}

@Composable
fun LoginButton(
    isLoginButtonEnabled: Boolean,
    loginViewModel: UserLoginViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                loginViewModel.loginUser()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 70.dp)
                .height(60.dp),
            enabled = isLoginButtonEnabled
        ) {
            Text(
                text = "Login",
                fontSize = 25.sp
            )
        }
        Text(
            text = "Forgot Password?",
            modifier = Modifier.padding(top = 15.dp)
        )
    }
}
