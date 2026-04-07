package com.example.voiceup.domain.usecase

import javax.inject.Inject

data class IssuesUseCases @Inject constructor(
    val addUseCase: AddUseCase,
    val deleteUseCase: DeleteUseCase,
    val updateUseCase: UpdateUseCase,
    val getAllUseCase: GetAllUseCase,   // student issues
    val syncIssuesUseCase:SyncIssuesUseCase,
    val getAllIssuesUseCase: GetAllIssueUseCase // teacher issues
)
