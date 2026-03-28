package com.example.voiceup.domain.usecase

data class IssuesUseCases(
    val addUseCase: AddUseCase,
    val deleteUseCase: DeleteUseCase,
    val updateUseCase: UpdateUseCase,
    val getAllUseCase: GetAllUseCase
)
