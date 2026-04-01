package com.example.voiceup.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.voiceup.domain.Issue
import com.example.voiceup.ui.theme.primarycolor
import com.example.voiceup.ui.theme.secondarycolor
import com.example.voiceup.ui.theme.tertiarycolor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssueListScreen(
    navController: NavHostController,
    issueViewModel: IssueViewModel
) {

    val issueList by issueViewModel.issues.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("form")
                },
                containerColor = secondarycolor,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                color = primarycolor,
                thickness = DividerDefaults.Thickness,
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (issueList.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Empty list!", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(issueList) { issue ->
                        IssueCard(issue)
                    }
                }
            }
        }
    }
}

@Composable
fun IssueCard(issue: Issue) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(tertiarycolor),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(23.dp)
    ) {

        ConstraintLayout (
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .padding(16.dp)
        ) {

            val (subject, button, detail) = createRefs()


            Text(
                text = issue.subject,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.constrainAs(subject) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(button.start)
                    width = Dimension.fillToConstraints
                }
            )

            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.constrainAs(button) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
            ) {
                Icon(
                    imageVector = if (expanded)
                        Icons.Filled.KeyboardArrowUp
                    else
                        Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }

            if (expanded) {
                Column(
                    modifier = Modifier.constrainAs(detail) {
                        start.linkTo(parent.start)
                        top.linkTo(subject.bottom, margin = 8.dp)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                ) {
                    Text(
                        text = "Your Issue:",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = issue.issue,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

