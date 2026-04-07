package com.example.voiceup.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voiceup.domain.Issue
import com.example.voiceup.domain.repo.AuthRepository
import com.example.voiceup.domain.usecase.IssuesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(
    private val issuesUseCases: IssuesUseCases,
    private val authRepository: AuthRepository
) : ViewModel() {

    var prn by mutableStateOf("")
    var name by mutableStateOf("")
    var subject by mutableStateOf("")
    var issue by mutableStateOf("")

    private val currentUserId = authRepository
        .observeAuthState()

    @OptIn(ExperimentalCoroutinesApi::class)
    val issues = currentUserId.flatMapLatest { uid ->
        if (uid == null) {
            flowOf(emptyList())
        } else {
            issuesUseCases.getAllUseCase.execute(uid)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    // Teacher issues (all users issues from firebase)
    val allIssues = issuesUseCases.getAllIssuesUseCase.execute()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )


    fun addIssue() {
        viewModelScope.launch {
            val uid = currentUserId.first() ?: return@launch
            val newIssue = Issue(
                name = name,
                prn = prn,
                subject = subject,
                issue = issue,
                userId = uid
            )
            issuesUseCases.addUseCase.execute(newIssue)
            clearFields()
        }
    }
    fun clearFields() {
        name = ""
        prn = ""
        subject = ""
        issue = ""
    }


    init {
        viewModelScope.launch {
            currentUserId.collectLatest { uid ->
                if (uid != null) {
                    issuesUseCases.syncIssuesUseCase.execute(uid)
                }
            }
        }
    }

    fun updateIssue(issue: Issue) {
        viewModelScope.launch {
            val uid = currentUserId.first()?: return@launch
            issuesUseCases.updateUseCase.execute(issue.copy(userId = uid))
        }
    }

    fun deleteIssue(issue: Issue) {
        viewModelScope.launch {
            val uid = currentUserId.first() ?: return@launch
            issuesUseCases.deleteUseCase.execute(issue.copy(userId = uid))
        }
    }
}