package com.example.voiceup.domain.usecase

import com.example.voiceup.domain.Issue
import com.example.voiceup.domain.repo.IssueRepository
import javax.inject.Inject
class DeleteUseCase @Inject constructor(
    private val issueRepository: IssueRepository
) {
    suspend fun execute(issue: Issue) {
        issueRepository.deleteIssue(issue)
    }
}