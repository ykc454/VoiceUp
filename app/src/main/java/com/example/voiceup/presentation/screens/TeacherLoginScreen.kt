package com.example.voiceup.presentation.screens

//google login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.voiceup.R
import com.example.voiceup.presentation.viewmodels.AuthViewModel
import com.example.voiceup.ui.theme.continuegooglecolor
import com.example.voiceup.ui.theme.primarycolor
import com.example.voiceup.ui.theme.secondarycolor
import com.example.voiceup.ui.theme.tertiarycolor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//next


@Composable
fun TeacherLoginScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authViewModel: AuthViewModel = hiltViewModel()
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState).imePadding(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
            Box(
                modifier = Modifier.fillMaxWidth(),

                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.clg_logo),
                        contentDescription = "College Logo",
                        modifier = Modifier
                            .height(280.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(54.dp))


                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            scope.launch {
                                delay(30)
                                scrollState.animateScrollTo(scrollState.maxValue)
                            }
                        },
                        label = { Text("Email") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = secondarycolor,
                            unfocusedLabelColor = Color.Gray,
                            focusedLabelColor = primarycolor,
                            focusedContainerColor = tertiarycolor,
                            unfocusedContainerColor = tertiarycolor,
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = { Text("Password") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),

                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = secondarycolor,
                            focusedLabelColor = primarycolor,
                            focusedContainerColor = tertiarycolor,
                            unfocusedContainerColor = tertiarycolor,
                            unfocusedLabelColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))


                    //Login Button
                    Button(
                        onClick = {
                            authViewModel.login(email, password) {

                                if (email == "rmw@gmail.com") {
                                    Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT)
                                        .show()
                                    navController.navigate(Screen.Operator.route) {
                                        popUpTo(Screen.TeacherLogin.route) { inclusive = true }
                                    }
                                } else {
                                    Toast.makeText(context, "Please Enter Valid Information", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(primarycolor)
                    ) {
                        if (authViewModel.isLoading) {
                            Box(
                                modifier = Modifier.fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(35.dp)
                                )
                            }

                        } else {
                            Text("Login", fontSize = 18.sp)
                        }
                    }
                    authViewModel.errorMessage?.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}