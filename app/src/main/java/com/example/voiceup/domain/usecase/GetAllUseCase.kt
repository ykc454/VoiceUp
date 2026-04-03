package com.example.voiceup.domain.usecase

import com.example.voiceup.domain.Issue
import com.example.voiceup.domain.IssueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUseCase @Inject constructor(
    private val issueRepository: IssueRepository
) {
    fun execute(userId: String): Flow<List<Issue>> {
        return issueRepository.getIssue(userId)
    }
}