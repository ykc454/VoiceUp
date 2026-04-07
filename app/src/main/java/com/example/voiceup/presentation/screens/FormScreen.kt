package com.example.voiceup.presentation.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.voiceup.presentation.viewmodels.IssueViewModel
import com.example.voiceup.ui.theme.primarycolor
import com.example.voiceup.ui.theme.secondarycolor
import com.example.voiceup.ui.theme.tertiarycolor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FormScreen(
    navController: NavHostController,
    issueViewModel: IssueViewModel
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()




    BackHandler {
        navController.popBackStack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {


        HorizontalDivider(
            color = primarycolor,
            thickness = DividerDefaults.Thickness
        )

        // Name
        OutlinedTextField(
            value = issueViewModel.name,
            onValueChange = { issueViewModel.name = it },
            label = { Text("Name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp)
        )

        // PRN
        OutlinedTextField(
            value = issueViewModel.prn,
            onValueChange = { issueViewModel.prn = it },
            label = { Text("College PRN") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp)
        )

        // Subject
        OutlinedTextField(
            value = issueViewModel.subject,
            onValueChange = { issueViewModel.subject = it },
            label = { Text("Subject") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp)
        )

        //ISSUE FIELD
        OutlinedTextField(
            value = issueViewModel.issue,
            onValueChange = {
                issueViewModel.issue = it

                // Scroll automatic when it text field goes behind key board
                scope.launch {
                    delay(50)
                    scrollState.animateScrollTo(scrollState.maxValue)
                }
            },
            label = { Text("Your Issue") },
            maxLines = 10,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun BottomAppBar(
    navController: NavHostController,
    issueViewModel: IssueViewModel
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {


        Button(
            onClick = {
                if (
                    issueViewModel.name.isBlank() ||
                    issueViewModel.prn.isBlank() ||
                    issueViewModel.subject.isBlank() ||
                    issueViewModel.issue.isBlank()
                ) {
                    Toast.makeText(context, "Fill all fields!", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                issueViewModel.addIssue()

                Toast.makeText(context, "Successfully Submitted!", Toast.LENGTH_SHORT).show()

                navController.navigate(Screen.IssueList.route) {
                    popUpTo(Screen.Form.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(primarycolor)
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun textFieldColors() = TextFieldDefaults.colors(
    focusedIndicatorColor = secondarycolor,
    focusedLabelColor = primarycolor,
    focusedContainerColor = tertiarycolor,
    unfocusedContainerColor = tertiarycolor,
    unfocusedLabelColor = Color.DarkGray
)