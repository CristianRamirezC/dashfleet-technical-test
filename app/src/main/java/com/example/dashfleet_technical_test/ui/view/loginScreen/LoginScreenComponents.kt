package com.example.dashfleet_technical_test.ui.view.loginScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.dashfleet_technical_test.core.constants.AppStringConstants
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel

@Composable
fun LoginTopBar() {
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
                text = AppStringConstants.APP_NAME,
                color = Color.White,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Black
            )
            Text(
                text = AppStringConstants.LOGIN,
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
        Text(text = AppStringConstants.PHONE_NUMBER)
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
fun PasswordField(userPassword: String, onTextChanged: (String) -> Unit) {
    var isPasswordVisible: Boolean by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 15.dp, end = 15.dp)
    ) {
        Text(text = AppStringConstants.PASSWORD)
        OutlinedTextField(
            value = userPassword, onValueChange = {
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
                        contentDescription = AppStringConstants.SHOW_PASSWORD
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
fun RememberUserToggle(isErrorLogging: Boolean) {
    var rememberToggle: Boolean by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
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
            Text(text = AppStringConstants.REMEMBER)
        }
        if (isErrorLogging) {
            Text(
                modifier = Modifier,
                text = AppStringConstants.LOGIN_ERROR,
                color = Color.Red,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LoginButton(
    isLoginButtonEnabled: Boolean,
    loginViewModel: UserLoginViewModel,
    isLoading: Boolean
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
            if (!isLoading) {
                Text(
                    text = AppStringConstants.LOGIN,
                    fontSize = 25.sp
                )
            } else {
                Box(modifier = Modifier) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
            }

        }
        Text(
            text = AppStringConstants.FORGOT_PASSWORD,
            modifier = Modifier.padding(top = 15.dp)
        )
    }
}