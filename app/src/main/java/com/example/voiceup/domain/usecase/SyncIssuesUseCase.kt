package com.example.voiceup.domain.usecase

import com.example.voiceup.domain.repo.IssueRepository
import javax.inject.Inject

class SyncIssuesUseCase @Inject constructor(
    private val repository: IssueRepository
) {
    suspend fun execute(userId: String) {
        repository.syncIssues(userId)
    }
}