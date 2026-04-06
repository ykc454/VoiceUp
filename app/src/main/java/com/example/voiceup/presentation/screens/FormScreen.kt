package com.example.voiceup.presentation.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.voiceup.domain.Issue
import com.example.voiceup.ui.theme.primarycolor
import com.example.voiceup.ui.theme.secondarycolor
import com.example.voiceup.ui.theme.tertiarycolor
import androidx.activity.compose.BackHandler
import com.example.voiceup.presentation.viewmodels.IssueViewModel
import kotlinx.coroutines.launch

@Composable
fun FormScreen(
    navController: NavHostController,
    issueViewModel: IssueViewModel
) {
    BackHandler {
        navController.popBackStack()
    }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(
                    color = primarycolor,
                    thickness = DividerDefaults.Thickness,
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = issueViewModel.name,
                    onValueChange = { issueViewModel.name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = secondarycolor,
                        focusedLabelColor = primarycolor,
                        focusedContainerColor = tertiarycolor,
                        unfocusedContainerColor = tertiarycolor,
                        unfocusedLabelColor = Color.DarkGray
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = issueViewModel.prn,
                    onValueChange = { issueViewModel.prn = it },
                    label = { Text("College PRN") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = secondarycolor,
                        focusedLabelColor = primarycolor,
                        focusedContainerColor = tertiarycolor,
                        unfocusedContainerColor = tertiarycolor,
                        unfocusedLabelColor = Color.DarkGray
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = issueViewModel.subject,
                    onValueChange = { issueViewModel.subject = it },
                    label = { Text("Subject") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = secondarycolor,
                        focusedLabelColor = primarycolor,
                        focusedContainerColor = tertiarycolor,
                        unfocusedContainerColor = tertiarycolor,
                        unfocusedLabelColor = Color.DarkGray
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = issueViewModel.issue,
                    onValueChange = { issueViewModel.issue = it },
                    label = { Text("Your Issue") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = secondarycolor,
                        focusedLabelColor = primarycolor,
                        focusedContainerColor = tertiarycolor,
                        unfocusedContainerColor = tertiarycolor,
                        unfocusedLabelColor = Color.DarkGray
                    )
                )
                Spacer(modifier = Modifier.height(22.dp))
            }
        }

}





@Composable
fun BottomAppBar(
    navController: NavHostController,
    issueViewModel: IssueViewModel
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedButton(
            onClick = {
                navController.navigate(Screen.IssueList.route) {
                    popUpTo(Screen.Form.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = primarycolor)
        ) {
            Text(text = "Back", maxLines = 1)
        }

        Button(
            onClick = {

                if (issueViewModel.name.isBlank() ||
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
            Text(text = "Submit", maxLines = 1)
        }
    }
}