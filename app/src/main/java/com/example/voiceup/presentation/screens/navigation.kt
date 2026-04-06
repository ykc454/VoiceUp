package com.example.voiceup.presentation.screens

import com.example.voiceup.R

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.voiceup.presentation.viewmodels.AuthViewModel
import com.example.voiceup.presentation.viewmodels.IssueViewModel
import com.example.voiceup.ui.theme.primarycolor
sealed class Screen(val route: String, @StringRes val titleRes: Int) {
    object Login : Screen("login", R.string.title_login)
    object SignUp : Screen("signup", R.string.title_signup)
    object Form : Screen("form", R.string.title_form)
    object IssueList : Screen("issue_list", R.string.title_issue_list)

    companion object {
        // Helper to find a Screen object by its route string
        fun fromRoute(route: String?): Screen = when (route) {
            SignUp.route -> SignUp
            Form.route -> Form
            IssueList.route -> IssueList
            else -> Login
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssueApp() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val issueViewModel: IssueViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()
    val currentScreen = Screen.fromRoute(currentRoute)
    val toptext = stringResource(id = currentScreen.titleRes)

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(100.dp),
                title = {
                    Box(
                        modifier = Modifier
                            .wrapContentHeight(Alignment.CenterVertically)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .padding(horizontal = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = toptext,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )

                            if (currentRoute == "issue_list") {
                                OutlinedButton(
                                    onClick = {
                                        authViewModel.logout()
                                        navController.navigate("login") {
                                            popUpTo("issue_list") {
                                                inclusive = true
                                            }
                                        }
                                    },
                                    modifier = Modifier.height(45.dp).padding(end = 25.dp),
                                    shape = RoundedCornerShape(12.dp), // nicer shape
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = primarycolor
                                    )
                                ) {
                                    Text("Logout")
                                }
                            }
                        }

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(

                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            when (currentRoute) {

                "form" -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .navigationBarsPadding()
                    ) {
                        BottomAppBar(
                            navController = navController,
                            issueViewModel = issueViewModel
                        )
                    }
                }

                else -> {
                    // No bottom bar for other screens
                }
            }
        }


    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
        ) {
            NavHost(navController, startDestination = Screen.Login.route) {
                composable(Screen.Login.route) {
                    LoginScreen(navController)
                }
                composable(Screen.SignUp.route) {
                    SignUpScreen(navController)
                }
                composable(Screen.Form.route) {
                    FormScreen(navController, issueViewModel)
                }
                composable(Screen.IssueList.route) {
                    IssueListScreen(navController, issueViewModel)

                }
            }
        }
    }

}
