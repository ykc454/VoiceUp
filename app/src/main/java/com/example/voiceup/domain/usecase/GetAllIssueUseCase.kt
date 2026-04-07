package com.example.voiceup.domain.usecase

import com.example.voiceup.domain.Issue
import com.example.voiceup.domain.repo.IssueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllIssueUseCase @Inject constructor(
    private val repository: IssueRepository
) {
    fun execute(): Flow<List<Issue>> {
        return repository.getAllIssues()
    }
}