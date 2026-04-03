package com.example.voiceup.domain.usecase

import com.example.voiceup.domain.Issue
import com.example.voiceup.domain.IssueRepository
import javax.inject.Inject

class AddUseCase @Inject constructor(
    private val issueRepository: IssueRepository
) {
    suspend fun execute(issue: Issue) {
        issueRepository.addIssue(issue)
    }
}