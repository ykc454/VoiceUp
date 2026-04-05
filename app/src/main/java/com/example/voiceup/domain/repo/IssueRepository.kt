package com.example.voiceup.domain.repo

import com.example.voiceup.domain.Issue
import kotlinx.coroutines.flow.Flow

interface IssueRepository {
    fun getIssue(userId: String): Flow<List<Issue>>
    suspend fun addIssue(issue: Issue)
    suspend fun updateIssue(issue: Issue)
    suspend fun deleteIssue(issue: Issue)
}