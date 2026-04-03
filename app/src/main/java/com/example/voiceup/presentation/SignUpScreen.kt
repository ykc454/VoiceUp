package com.example.voiceup.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.voiceup.R
import com.example.voiceup.ui.theme.primarycolor
import com.example.voiceup.ui.theme.secondarycolor
import com.example.voiceup.ui.theme.tertiarycolor
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun SignUpScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth()
                    ,
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
                    Spacer(modifier = Modifier.height(32.dp))
                    Text("SignUp", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = secondarycolor,
                            focusedLabelColor = primarycolor,
                            focusedContainerColor = tertiarycolor,
                            unfocusedContainerColor = tertiarycolor,
                            unfocusedLabelColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = secondarycolor,
                            focusedLabelColor = primarycolor,
                            focusedContainerColor = tertiarycolor,
                            unfocusedContainerColor = tertiarycolor,
                            unfocusedLabelColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    val context = LocalContext.current

                    Button(onClick = {
                        Firebase.auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                                    navController.navigate("login") {
                                        popUpTo("signup") {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    Toast.makeText(context, task.exception?.message ?: "Sign Up Failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                    },modifier = Modifier.fillMaxWidth().height(50.dp),
                        colors = ButtonDefaults.buttonColors(primarycolor)
                    ) {
                        Text("Sign Up", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(onClick = {
                        navController.navigate("login") {
                            popUpTo("signup") {
                                inclusive = true
                            }
                        }
                    }
                    ) {
                       Text("Already have an account? Login")
                    }
                }
            }
        }
    }
}