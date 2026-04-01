package com.example.voiceup.domain

import kotlinx.coroutines.flow.Flow

interface IssueRepository {
    fun getIssue(): Flow<List<Issue>>
    suspend fun addIssue(issue: Issue)
    suspend fun updateIssue(issue:Issue)
    suspend fun deleteIssue(issue: Issue)
}