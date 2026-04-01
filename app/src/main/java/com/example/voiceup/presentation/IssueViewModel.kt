package com.example.voiceup.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voiceup.domain.Issue
import com.example.voiceup.domain.usecase.IssuesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(
    private val issuesUseCases: IssuesUseCases
) : ViewModel() {

    var prn by  mutableStateOf("")
    var name by  mutableStateOf("")
    var subject by  mutableStateOf("")
    var issue by  mutableStateOf("")

    val issues = issuesUseCases.getAllUseCase.execute()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addIssue(issue: Issue){
        viewModelScope.launch {
            issuesUseCases.addUseCase.execute(issue)
        }
    }

    fun updateIssue(issue: Issue){
        viewModelScope.launch {
            issuesUseCases.updateUseCase.execute(issue)
        }
    }

    fun deleteIssue(issue: Issue){
        viewModelScope.launch {
            issuesUseCases.deleteUseCase.execute(issue)
        }
    }
}