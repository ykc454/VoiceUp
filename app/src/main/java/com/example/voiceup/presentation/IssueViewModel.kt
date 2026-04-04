package com.example.voiceup.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voiceup.domain.Issue
import com.example.voiceup.domain.usecase.IssuesUseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(
    private val issuesUseCases: IssuesUseCases
) : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    var prn by mutableStateOf("")
    var name by mutableStateOf("")
    var subject by mutableStateOf("")
    var issue by mutableStateOf("")

    // Track the current user ID reactively
    private val _currentUserId = MutableStateFlow(auth.currentUser?.uid ?: "")

    init {
        // Update user ID whenever auth state changes
        auth.addAuthStateListener { firebaseAuth ->
            _currentUserId.value = firebaseAuth.currentUser?.uid ?: ""
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val issues = _currentUserId.flatMapLatest { uid ->
        issuesUseCases.getAllUseCase.execute(uid)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addIssue(issue: Issue) {
        viewModelScope.launch {
            val uid = auth.currentUser?.uid ?: return@launch
            issuesUseCases.addUseCase.execute(issue.copy(userId = uid))
        }
    }

    fun updateIssue(issue: Issue) {
        viewModelScope.launch {
            val uid = auth.currentUser?.uid ?: return@launch
            issuesUseCases.updateUseCase.execute(issue.copy(userId = uid))
        }
    }

    fun deleteIssue(issue: Issue) {
        viewModelScope.launch {
            val uid = auth.currentUser?.uid ?: return@launch
            issuesUseCases.deleteUseCase.execute(issue.copy(userId = uid))
        }
    }
}